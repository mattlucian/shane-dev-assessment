package com.is.dev.assessment.util;

import com.is.dev.assessment.domain.Product;

import java.io.FileWriter;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//title,upc,sku,price,condition,quantity
public class ProductJsonUtil {
    public static void bounceToJson(Product[] finalProducts){
        JSONObject obj = new JSONObject();

        for (Product product : finalProducts) {
            JSONArray entry = new JSONArray();
            entry.add( product.title );
            entry.add( product.upc );
            entry.add( product.sku );
            entry.add( product.price );
            entry.add( product.condition );
            entry.add( product.quantity );
            obj.put( product.sku, entry);
        }
        
        try{
            FileWriter file = new FileWriter("outputFiles/products.json");
            file.write(obj.toJSONString());
            System.out.println("Successfully converted products to JSON File at outputFiles/products.json");
            file.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
