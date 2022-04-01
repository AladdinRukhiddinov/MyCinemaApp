package uz.pdp.mycinemaapp.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.mycinemaapp.entity.User;
import uz.pdp.mycinemaapp.projection.TicketProjection;
import uz.pdp.mycinemaapp.repository.TicketRepository;
import uz.pdp.mycinemaapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/purchase")
@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Value("${STRIPE_API_KEY}")
    String stripeApiKey;

    @GetMapping
    public ResponseEntity<?> createStripeSession() {

        Stripe.apiKey = stripeApiKey;

        Optional<User> test = userRepository.findByUserName("test");
        List<TicketProjection> ticketByUserIdList = ticketRepository.getTicketByUserId(test.get().getId());

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (TicketProjection ticketProjection : ticketByUserIdList) {
            Double price = ticketProjection.getPrice();
            String title = ticketProjection.getTitle();

            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(title)
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setCurrency("usd")
                    .setUnitAmount((long)(price*100))
                    .setProductData(productData)
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();

            lineItems.add(lineItem);
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success?userId="+test.get().getId().toString())
                .addAllLineItem(lineItems)
                .setClientReferenceId(test.get().getId().toString())
                .build();

        try {
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();
            return ResponseEntity.ok(checkoutUrl);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        ResponseEntity.badRequest().build();
        return null;
    }


}
