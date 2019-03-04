package com.is.dev.assessment.util;

import com.is.dev.assessment.domain.Product;

import java.io.FileWriter;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//title,upc,sku,price,condition,quantity
public class ProductJsonUtil {

    public static void bounceToJson(Product[] finalProducts){
        JSONObject obj = new JSONObject();

        // This actually doesn't return the correct structure.

        // It returns an array with the attributes in it, not a "product" object with key/value pairs.
            // see below for correct JSON structure of these objects

        for (Product product : finalProducts) {
            JSONArray entry = new JSONArray();
            entry.add( product.getTitle() );
            entry.add( product.getUpc() );
            entry.add( product.getSku());
            entry.add( product.getPrice() );
            entry.add( product.getCondition());
            entry.add( product.getQuantity() );
            obj.put( product.getSku(), entry);
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

    public static void bounceToJson(Set<Product> products) throws Exception {
        JSONArray jsonArray = new JSONArray();

        jsonArray.addAll(
            products.stream()
                .map(p -> {
                    JSONObject o = new JSONObject();
                    o.put("title", p.getTitle());
                    o.put("sku", p.getSku());
                    o.put("quantity", p.getQuantity());
                    o.put("upc", p.getUpc());
                    o.put("price", p.getPrice());
                    o.put("condition", p.getCondition());
                    return o;
                })
                .collect(Collectors.toList())
        );

        // re-use existing util
        FileUtil.writeObjectToFile(
            jsonArray.toString(), "outputFiles/products.json"
        );

    }

}
