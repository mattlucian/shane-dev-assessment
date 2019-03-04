package com.is.dev.assessment.util;

import com.is.dev.assessment.domain.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;


//title,upc,sku,price,condition,quantity

public class ProductXmlUtil {
    public static void bounceToXML(Product[] finalProducts){
        
      try {
          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.newDocument();
         
         Element rootElement = doc.createElement("products");
         doc.appendChild(rootElement);

          for (Product product : finalProducts) {
              Element baseEntry = doc.createElement( product.sku );
              rootElement.appendChild(baseEntry);
              
              Element title = doc.createElement("title");
              title.appendChild(doc.createTextNode(product.title));
              baseEntry.appendChild(title);
              
              Element upc = doc.createElement("upc");
              upc.appendChild(doc.createTextNode(product.upc));
              baseEntry.appendChild(upc);
              
              Element sku = doc.createElement("sku");
              sku.appendChild(doc.createTextNode(product.sku));
              baseEntry.appendChild(sku);
              
              Element price = doc.createElement("price");
              price.appendChild(doc.createTextNode(product.price));
              baseEntry.appendChild(price);
              
              Element condition = doc.createElement("condition");
              condition.appendChild(doc.createTextNode(product.condition));
              baseEntry.appendChild(condition);
              
              Element quantity = doc.createElement("quantity");
              quantity.appendChild(doc.createTextNode(product.quantity));
              baseEntry.appendChild(quantity);
          }
         
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(new File("outputFiles/products.xml"));
         System.out.println("Successfully converted products to XML File at outputFiles/products.xml");
         transformer.transform(source, result); 

      } catch (Exception e) {
        System.out.println(e.toString());
      }
    } 
}
