package com.is.dev.assessment;

import com.is.dev.assessment.util.*;
import com.is.dev.assessment.domain.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.*;
import com.opencsv.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;


public class Main {
    static HashSet alreadyProcessedSKUs, SKUsToProcessThisRun;
    static Map<String, String[]> finalEntryData;
    static Map<String, Integer> highestEntryDataSourceAccuracy;
    static Product[] alreadySavedProducts, finalProducts;


    /**
     *
     * The goals :
     *
     * - Have an "accuracy" component for incoming files
     * - Parse through the "csv" or "tsv" files in the folder
     * - Output this data in XML / JSON
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        // create a process to work with
        AssessmentProcess process = new AssessmentProcess();

        // configure the process
        try{
            process.configure();

        }catch (Exception ex){
            System.err.println("Failed to configure data accuracy, exiting");
            ex.printStackTrace();
            return;
        }


        // parse files from the process
        try{
            process.parseFiles();

        }catch (Exception ex){
            System.err.println("Failed to process data files, exiting");
            ex.printStackTrace();
            return;
        }


        // output files from the process
        try{
            process.outputFiles();

        }catch (Exception ex){
            System.err.println("Failed to putput data files, exiting");
            ex.printStackTrace();
        }

    }

    
}
