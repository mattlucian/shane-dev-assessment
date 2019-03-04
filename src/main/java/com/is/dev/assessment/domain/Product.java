package com.is.dev.assessment.domain;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Product implements java.io.Serializable {

    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "upc")
    private String upc;
    @CsvBindByName(column = "sku")
    private String sku;
    @CsvBindByName(column = "price")
    private String price;
    @CsvBindByName(column = "condition")
    private String condition;
    @CsvBindByName(column = "quantity")
    private String quantity;

    //I don't know why people like to make private variables and then make getters and setters.
    //I can easily adjust to such practice, but I think it's faster and more efficient to make only those variables that
    //aren't desired to be accessed or used private in the first place.


    // ^ This protects the value at runtime so it cannot be accessed and manipulated. Also a variety of other benefits. check out :
        // https://www.quora.com/What-is-the-use-of-getters-and-setters-in-java
    // C# does this by default, for example, if you put { getter; setter; }, but it's doing the same fundamental thing behind the scenes


    public Product(){

    }

    public Product(String title, String upc, String sku, String price, String condition, String quantity){
        
        this.title = title;
        this.upc = upc;
        this.sku = sku;
        this.price = price;
        this.condition = condition;
        this.quantity = quantity;
    }

    // auto-generated below with IntelliJ IDEA Ultimate

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }



    /***
     *
     * The "equals" and "hashCode" methods are used in the concept of a "HashSet"
     *
     * The below methods are basically saying : "The SKU is unique" -> so if the SKU is the same, the objects should be
     *  considered the same.
     *
     *  So if we call "set.add(product)" and the sku is already in the "set" object, then this product will NOT be added
     *   because of these two methods
     *
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
