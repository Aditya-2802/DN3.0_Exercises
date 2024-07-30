package AdapterPatternExample;

// AdapterPatternTest.java
public class AdapterPatternTest {
    public static void main(String[] args) {
        // Using PayPal gateway
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalGateway());
        payPalProcessor.processPayment(100.0);

        // Using Stripe gateway
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(200.0);
    }
}
