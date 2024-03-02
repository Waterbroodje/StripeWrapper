package me.waterbroodje.session;

import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SessionBuilder {

    private String cancelUrl;
    private String successUrl;
    private String returnUrl;
    private String customerEmail;
    private Boolean allowPromotionCodes;
    private Boolean customerCreation;
    private Boolean billingAddressCollection;
    private Boolean phoneNumberCollection;
    private String price;
    private SessionCreateParams.Mode stripeMode;
    private Long quantity = 1L;

    public Session build() {
        SessionCreateParams.Builder builder = SessionCreateParams.builder();

        if (cancelUrl != null) {
            builder.setCancelUrl(cancelUrl);
        }
        if (successUrl != null) {
            builder.setSuccessUrl(successUrl);
        }
        if (returnUrl != null) {
            builder.setReturnUrl(returnUrl);
        }
        if (customerEmail != null) {
            builder.setCustomerEmail(customerEmail);
        }
        if (allowPromotionCodes != null) {
            builder.setAllowPromotionCodes(allowPromotionCodes);
        }
        if (customerCreation != null) {
            builder.setCustomerCreation(customerCreation ? SessionCreateParams.CustomerCreation.ALWAYS : SessionCreateParams.CustomerCreation.IF_REQUIRED);
        }
        if (billingAddressCollection != null) {
            builder.setBillingAddressCollection(billingAddressCollection ? SessionCreateParams.BillingAddressCollection.REQUIRED : SessionCreateParams.BillingAddressCollection.AUTO);
        }
        if (phoneNumberCollection != null) {
            builder.setPhoneNumberCollection(SessionCreateParams.PhoneNumberCollection.builder()
                    .setEnabled(phoneNumberCollection).build());
        }
        if (stripeMode != null) {
            builder.setMode(stripeMode);
        }
        if (price != null && quantity != null) {
            builder.addLineItem(SessionCreateParams.LineItem.builder()
                    .setPrice(price)
                    .setQuantity(quantity)
                    .build());
        }

        try {
            com.stripe.model.checkout.Session stripeSession = com.stripe.model.checkout.Session.create(builder.build());
            return new Session(stripeSession, price, quantity);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe session", e);
        }
    }
}