package me.waterbroodje.session;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private com.stripe.model.checkout.Session stripeSession;
    private String customerEmail;

    private String price;
    private Long quantity;

    private String sessionUrl;
    private String sessionId;

    public Session(com.stripe.model.checkout.Session stripeSession, String price, Long quantity) {
        this.stripeSession = stripeSession;

        this.customerEmail = stripeSession.getCustomerEmail();

        this.price = price;
        this.quantity = quantity;

        this.sessionUrl = stripeSession.getUrl();
        this.sessionId = stripeSession.getId();
    }
}