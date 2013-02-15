package com.example.WidgetWallPaper;


import android.util.Log;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WallHistory {
    final String TAG = "AppHistory";
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Element element;
    Document doc;
    String filepath = "/data/data/com.example.WidgetWallPaper/files/";
    String filename = "history.xml";
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


            Element childelement = doc.createElement("image");
            element.appendChild(childelement);

            Attr attr = doc.createAttribute("link");
            attr.setValue(url);
            childelement.setAttributeNode(attr);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            File file;
            file = new File(filepath, filename);

            StreamResult sr = new StreamResult(file);
            transformer.transform(domSource, sr);
            Log.v(TAG, "История сохранена");
        } catch (TransformerConfigurationException tce){

        } catch (TransformerException te){
            Log.e("Error", String.valueOf(te));
        }
        //getUrl();
    }

    public String getUrl(){
        String url = null;
        try{
            Log.v("APPLICATION","Файл: " + filepath+filename);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(filepath+filename);
            doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("image");
            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                Log.v("APPLICATION","Элемент: " + node.getNodeName());
                Element getelement = (Element)node;
                url = getelement.getAttribute("link");
                Log.v("APPLICATION","Аттрибур: " + getelement.getAttribute("link"));
            }
        }
        catch (ParserConfigurationException pce) {
            Log.e("APPLICATION","ОШИБКА ПОЛУЧЕНИЯ АТТРИБУТА:",pce);
        }
        catch (Exception e) {
            Log.e("APPLICATION","ОШИБКА ПОЛУЧЕНИЯ АТТРИБУТА:",e);
        }
        return url;
    }
}
