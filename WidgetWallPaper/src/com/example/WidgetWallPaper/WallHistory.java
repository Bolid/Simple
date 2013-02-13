package com.example.WidgetWallPaper;


import android.util.Log;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class WallHistory {

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Element element;
    Document doc;
    public void createdocumentHistory(){
        try{
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.newDocument();
            element = doc.createElement("session");
            doc.appendChild(element);
        } catch (ParserConfigurationException pce){
        }

    }

    public void setUrl(String url){
        try {


            Element element1 = doc.createElement("image");
            element.appendChild(element1);

            Attr attr = doc.createAttribute("link");
            attr.setValue(url);
            element1.setAttributeNode(attr);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            File file;
            String path = "/data/data/com.example.WidgetWallPaper/files/";
            file = new File(path, "history.xml");

            StreamResult sr = new StreamResult(file);
            transformer.transform(domSource, sr);
        } catch (TransformerConfigurationException tce){

        } catch (TransformerException te){
            Log.e("Error", String.valueOf(te));
        }
    }
}
