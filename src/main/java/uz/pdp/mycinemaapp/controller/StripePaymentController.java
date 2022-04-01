package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripePaymentController {

    @Value("${STRIPE_API_KEY}")
    String stripeApiKey;

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public HttpEntity<?> getSuccessMsg() {

        return ResponseEntity.ok("Successfully payed!!!");
    }
}
