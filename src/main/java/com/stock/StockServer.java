package com.stock;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.ArrayList;

public class StockServer {

    public static void startServer(List<Stock> stocks) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8181), 0);

        server.createContext("/stocks", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                    exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
                    return;
                }

                List<StockDTO> stockDTOs = new ArrayList<>();
                for (Stock stock : stocks) {
                    stockDTOs.add(new StockDTO(stock.getName(), stock.getPrice()));
                }

                String json = new Gson().toJson(stockDTOs);

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                exchange.sendResponseHeaders(200, json.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            }
        });

        server.setExecutor(null); // use default executor
        server.start();
        System.out.println("Server started at http://localhost:8181/stocks");
    }

    // Inner DTO class for clean JSON output
    static class StockDTO {
        String name;
        double price;

        StockDTO(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}
