
package com.stock;

public class Stock {
  
    private final String name;
  
   private double price;
  
   public Stock(String name,double price)
   {
  
    this.name = name;
  
    this.price = price;
   }    
  
   public  synchronized void  updatePrice(){
  
    double currentPrice = (Math.random()-0.5)*5;
  
    price = Math.max(0,currentPrice+price);
   }
  
   public String getName(){
  
    return name;

   }
  
   public double getPrice(){
  
    return price;
   }
}
