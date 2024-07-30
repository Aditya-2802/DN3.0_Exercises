package StrategyPatternExample;

public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Paying with credit card
        PaymentStrategy creditCardPayment = new CreditCardPayment("John Doe", "1234567890123456", "123", "12/23");
        context.setPaymentStrategy(creditCardPayment);
        context.executePayment(100);

        // Paying with PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("johndoe@example.com", "password");
        context.setPaymentStrategy(payPalPayment);
        context.executePayment(200);
    }
}
