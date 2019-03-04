package com.is.dev.assessment.domain;

public class Product implements java.io.Serializable {
    public String title, upc, sku, price, condition, quantity;
    //I don't know why people like to make private variables and then make getters and setters.
    //I can easily adjust to such practice, but I think it's faster and more efficient to make only those variables that
    //aren't desired to be accessed or used private in the first place.


    // ^ This protects the value at runtime so it cannot be accessed and manipulated. It also allows for

    public Product(String title, String upc, String sku, String price, String condition, String quantity){
        
        this.title = title;
        this.upc = upc;
        this.sku = sku;
        this.price = price;
        this.condition = condition;
        this.quantity = quantity;
    }
    
    public String getTitle(){
        return title;
    }
}
