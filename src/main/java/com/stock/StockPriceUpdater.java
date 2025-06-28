package com.stock;
public class StockPriceUpdater extends Thread {

    private final Stock stock;
    
    private final StockLogger logger;
    
    public StockPriceUpdater(Stock stock,StockLogger logger)
    {
        this.stock = stock;
    
        this.logger = logger;
    }
    @Override  public void run()  {
        
        try{
           
            while(!Thread.currentThread().isInterrupted())
            {
                stock.updatePrice();
             
                logger.log(stock.getName(), stock.getPrice());
             
                Thread.sleep(1000);

            }
        }
       
        catch(InterruptedException ignored){}
    }
}
