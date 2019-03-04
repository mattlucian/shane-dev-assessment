package com.is.dev.assessment.util;

import com.is.dev.assessment.domain.Product;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<Product> importFrom(String filePath, char delimiter) throws Exception {

        CSVReader reader = new CSVReader(new FileReader(filePath), delimiter);
        //  although this is deprecated ^ it works for a simple assessment

        // instead of doing "addFinalData" we can return a list of products to interact with
        List<Product> products = new ArrayList<>();

        // before you were using "headerPassed" as a way to always skip the first line - we'll just explicitly skip it
        boolean firstLine = true;
        String [] line;
        while (( line = reader.readNext()) != null) {

            if(firstLine){
                firstLine = false;
                continue; // skip header row
            }

            // not enough properties to create a product
            if(line.length < 5){
                //todo, log something about this failing to import
                continue;
            }

            // add this product
            products.add(
                //todo, it's better to create a new Product() and then use "setTitle", etc.
                    // right now, we don't know if line[0] is setting the title, etc.
                    // product.setTitle( line[0] ); is much more clear even if it takes a little longer to type
                new Product(
                    line[0], line[1], line[2], line[3], line[4], line[5]
                )
            );
        }

        // return the list of products
        return products;
    }



}
