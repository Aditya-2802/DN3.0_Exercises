package ObserverPatternExample;

public class MobileApp implements Observer {
    private String stockName;
    private double stockPrice;

    @Override
    public void update(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        display();
    }

    private void display() {
        System.out.println("Mobile App: Stock " + stockName + " is now $" + stockPrice);
    }
}
