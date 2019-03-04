package com.is.dev.assessment.util;

import com.is.dev.assessment.domain.Product;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    /**
     *
     * Not using this - using the Data Binding method as an example
     *
     * @param filePath
     * @param delimiter
     * @return
     * @throws Exception
     */
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

            // add this product, mutliple methods :
            products.add(
                CsvUtil.mapProductBySetters(line)
            );

            // CsvUtil.mapProductByConstructor(line);

        }

        // return the list of products
        return products;
    }


    /**
     * This does the same thing as "importFrom" above -> however, this "binds" the data from the CSV file into the Product.class
     *
     * This works by using the @CsvBindByName annotations on the Product.class properties (view this to see)
     *
     *  -> Advantage : If you add more properties in the future, you DONT have to continuously expand the Product "constructor"
     *  -> Advantage : Maps to headers, so if the file structure changes in the future this will still work

     * @param filePath
     * @param delimiter
     * @return
     * @throws Exception
     */
    public static List<Product> importFromDataBinding(String filePath, char delimiter) throws Exception {
        return new CsvToBeanBuilder(new FileReader(filePath))
            .withSeparator(delimiter)
            .withType(Product.class)
            .build()
            .parse();
    }


    // ways to map product

    // by constructor & index :

    /**
     * Mapping product from array of values -> domain object via the Constructor
     *  -> Disadvantage : If you add more properties in the future, you have to continuously expand the "constructor"
     *  -> Disadvantage : Requires the "index" to be exactly matching, so if the headers get re-ordered this breaks
     * @param line
     * @return
     */
    public static Product mapProductByConstructor(String[] line){
        return new Product(
            line[0], line[1], line[2], line[3], line[4], line[5]
        );
    }

    /**
     * Mapping product from array of values -> domain object
     *  -> Advantage : If you add more properties in the future, you don't have to continuously expand the "constructor"
     *  -> Disadvantage : Requires the "index" to be exactly matching, so if the headers get re-ordered this breaks
     *
     * @param line
     * @return
     */
    public static Product mapProductBySetters(String[] line){
        Product product = new Product();
        product.setTitle(line[0]);
        product.setUpc(line[1]);
        product.setSku(line[2]);
        product.setPrice(line[3]);
        product.setCondition(line[4]);
        product.setQuantity(line[5]);
        return product;
    }




}
