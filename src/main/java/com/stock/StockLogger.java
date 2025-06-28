package com.stock;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

public class StockLogger {

    private final FileWriter writer;

    public StockLogger(String filename) throws IOException{

        writer  = new FileWriter(filename,true);
    }
    public synchronized void log(String stockName,double price)
    {
        try{

            String log = LocalDateTime.now() +" - " + stockName + ":$ " +
                        String.format("%.2f", price);

            writer.write(log);

            writer.flush();
        }
        catch(IOException e)
        {
            System.err.println(" Logging error " + e.getMessage());
        }
    }
    public synchronized void close() throws IOException{
        
        writer.close();
    }
    
}
