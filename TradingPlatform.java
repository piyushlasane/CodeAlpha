import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", name, symbol, price);
    }
}

class Market {
    private Map<String, Stock> stocks;
    private Random random;

    public Market() {
        stocks = new HashMap<>();
        random = new Random();
        initializeMarket();
    }

    private void initializeMarket() {
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        stocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        stocks.put("AMZN", new Stock("AMZN", "Amazon.com, Inc.", 3400.00));
        // Add more stocks as needed
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void updatePrices() {
        for (Stock stock : stocks.values()) {
            double change = (random.nextDouble() - 0.5) * 20; // Random price change
            stock.setPrice(stock.getPrice() + change);
        }
    }

    public void displayMarket() {
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
}

class Portfolio {
    private Map<String, Integer> holdings;
    private double cash;

    public Portfolio(double initialCash) {
        holdings = new HashMap<>();
        cash = initialCash;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost > cash) {
            System.out.println("Not enough cash to buy " + quantity + " shares of " + symbol);
            return;
        }
        cash -= cost;
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity, double price) {
        int availableShares = holdings.getOrDefault(symbol, 0);
        if (quantity > availableShares) {
            System.out.println("Not enough shares to sell " + quantity + " shares of " + symbol);
            return;
        }
        double revenue = quantity * price;
        cash += revenue;
        holdings.put(symbol, availableShares - quantity);
        System.out.println("Sold " + quantity + " shares of " + symbol);
    }

    public void displayPortfolio(Market market) {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double currentPrice = market.getStock(symbol).getPrice();
            System.out.printf("%s: %d shares @ $%.2f each\n", symbol, quantity, currentPrice);
        }
        System.out.printf("Cash: $%.2f\n", cash);
    }
}

public class TradingPlatform {
    public static void main(String[] args) {
        Market market = new Market();
        Portfolio portfolio = new Portfolio(10000); // Initial cash of $10,000

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Update Market Prices");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock != null) {
                        portfolio.buyStock(buySymbol, buyQuantity, buyStock.getPrice());
                    } else {
                        System.out.println("Invalid stock symbol");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null) {
                        portfolio.sellStock(sellSymbol, sellQuantity, sellStock.getPrice());
                    } else {
                        System.out.println("Invalid stock symbol");
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio(market);
                    break;
                case 5:
                    market.updatePrices();
                    System.out.println("Market prices updated.");
                    break;
                case 6:
                    scanner.close();
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
