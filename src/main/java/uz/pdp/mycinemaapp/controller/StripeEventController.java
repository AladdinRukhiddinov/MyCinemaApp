package uz.pdp.mycinemaapp.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uz.pdp.mycinemaapp.repository.TicketRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stripe-webhook")
public class StripeEventController {

    private final TicketRepository ticketRepository;

    @Value("${STRIPE_API_KEY}")
    String stripeApiKey;

    @Value("${WEBHOOK_SECRET_KEY}")
    String endpointSecret;

    @PostMapping
    public Object handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String sigHeader) {

        Stripe.apiKey = stripeApiKey;

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();

            return "";
        } catch (Exception e){
            e.printStackTrace();
        }

        if ("checkout.session.completed".equals(event.getType())){
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

            fullFillOrder(session);
        }

        System.out.println("Got payload..." + payload);
        return "";
    }

    public void fullFillOrder(Session session) {
        System.out.println(session.getClientReferenceId());
        System.out.println("FulFilling order...");
    }
}
