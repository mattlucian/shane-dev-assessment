package com.is.dev.assessment;

import com.is.dev.assessment.domain.Product;
import com.is.dev.assessment.util.CsvUtil;

import com.is.dev.assessment.util.ProductJsonUtil;
import com.is.dev.assessment.util.ProductXmlUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Map;

public class AssessmentProcess {

    private Map<String, Integer> dataAccuracy;

    private final HashSet<Product> products;

    public AssessmentProcess(){
        this.products = new HashSet<>();
    }


    /**
     * Optional configure method
     *
     * @throws Exception
     */
    public void configure() throws Exception {

        //this is where you could configure more processes if needed
    }


    /**
     * Parse any files in "data_To_Process" folder that match our header structure
     *
     * @throws Exception
     */
    public void parseFiles() throws Exception {
        // NOTE : not using the data accuracy part here, just going to import the .csv files first then the .tsv files

        // see what files are ready to process
        File[] files = new File("data_To_Process").listFiles();

        // get all the csv files first
        for (File file : files) {
            if(file.getName().endsWith(".csv")){
                this.products.addAll(
                    CsvUtil.importFrom(file.getPath() + file.getName(), ',')
                );
            }
        }

        // get all the tsv files second
        for(File file : files){
            if(file.getName().endsWith(".csv")){
                // already processed above
            }else if(file.getName().endsWith(".tsv")){
                // process here
                this.products.addAll(
                    CsvUtil.importFromDataBinding(file.getPath() + file.getName(), '\t')
                );
            }else{
                System.err.println("Skipping unrecognized file "+file.getName());
            }
        }
    }


    /***
     * Output all products found into JSON / XML files
     *
     * @throws Exception
     */
    public void outputFiles() throws Exception {

        // send to xml
        try{
            ProductXmlUtil.bounceToXML(this.products);

        }catch (Exception ex){
            System.err.println("Failed to write the products to XML "+ex.getMessage());
            throw ex;
        }


        // send to json
        try{
            ProductJsonUtil.bounceToJson(this.products);

        }catch (Exception ex){
            System.err.println("Failed to write the products to JSON "+ex.getMessage());
            throw ex;
        }
    }



}
