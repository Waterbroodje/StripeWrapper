package me.waterbroodje;

import com.stripe.Stripe;

public class StripeWrapper {

    public static void setApiKey(String apiKey) {
        Stripe.apiKey = apiKey;
    }
}