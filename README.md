# Stripe Wrapper
I've made a Stripe wrapper so I can easily create checkout pages in my Java projects.
## How to use:
Import into your project using Maven:

Then, you can simply create a SessionBuilder, fill in the details & create a Session. Here's an example:
```java
public static void main(String[] args) {
    setApiKey("{your api key}");

    SessionBuilder sessionBuilder = new SessionBuilder();
    sessionBuilder.setPrice("{price id}");
    sessionBuilder.setCustomerEmail("{customer email}");
    sessionBuilder.setAllowPromotionCodes(true);
    sessionBuilder.setBillingAddressCollection(true);
    sessionBuilder.setPhoneNumberCollection(true);
    sessionBuilder.setSuccessUrl("{success url}");
    sessionBuilder.setCancelUrl("{cancel url}");
    sessionBuilder.setReturnUrl("{return url}");
    sessionBuilder.setStripeMode(SessionCreateParams.Mode.PAYMENT);
    sessionBuilder.setQuantity(1L);
    sessionBuilder.setCustomerCreation(true);

    Session session = sessionBuilder.build();
    String url = session.getStripeSession().getUrl();

    System.out.println(url);
}
```
