package ObserverPatternExample;

public class ObserverPatternExample {
    public static void main(String[] args) {
        // Create stock market
        StockMarket stockMarket = new StockMarket("AAPL", 150.00);

        // Create observers
        MobileApp mobileApp = new MobileApp();
        WebApp webApp = new WebApp();

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Change stock price
        stockMarket.setStockPrice(155.00);

        // Change stock price again
        stockMarket.setStockPrice(160.00);
    }
}
