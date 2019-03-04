package com.is.dev.assessment.util;

import java.io.*;

public class FileUtil {

    public static void writeObjectToFile(Object object, String filePath) throws Exception {
        try(FileOutputStream fileOut = new FileOutputStream(filePath)){
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
        }
    }

    public static ObjectInputStream readObjectFromFile(String filepath) throws Exception {
        FileInputStream fi = new FileInputStream(new File(filepath));
        ObjectInputStream oi = new ObjectInputStream(fi);
        return oi;
    }


}
