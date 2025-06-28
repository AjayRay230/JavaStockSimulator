package com.stock;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
         List<Stock> stocks = Arrays.asList(
            new Stock("AAPL", 150),
            new Stock("GOOG", 2800),
            new Stock("AMZN", 3400)
        );
        StockLogger sl = new StockLogger("stocks_log.txt");
        List<Thread> threads = new ArrayList<>();
        for(Stock stock:stocks)
        {
            Thread t = new StockPriceUpdater(stock,sl);
                t.start();
                threads.add(t);
        }
        // Start HTTP Server
        StockServer.startServer(stocks);

        // Stop after 30 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (Thread t : threads) t.interrupt();
                try {
                    sl.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0); // Close app
            }
        }, 30_000);

    }
    
}
