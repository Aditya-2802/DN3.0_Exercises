package AdapterPatternExample;

// StripeGateway.java
public class StripeGateway {
    public void pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}
