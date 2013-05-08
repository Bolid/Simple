package com.example.WidgetWallPaper;


import android.os.Environment;
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
import java.util.ArrayList;
import java.util.List;

public class WallHistory {
    final String TAG = "AppHistory";
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Element element;
    Document doc;
    TransformerFactory transformerFactory;
    Transformer transformer;
    DOMSource domSource;
    StreamResult sr;
    String filepath = "/data/data/com.example.WidgetWallPaper/files/";
    String filename = "history.xml";
    File file = new File(filepath, filename);

    public void createdocumentHistory(){
         try{
             file = new File(filepath);
             file.mkdir();
             file = new File(filepath, filename);
             documentBuilder = documentBuilderFactory.newDocumentBuilder();
             doc = documentBuilder.newDocument();
             element = doc.createElement("session");
             doc.appendChild(element);
             transformerFactory = TransformerFactory.newInstance();
             transformer = transformerFactory.newTransformer();
             domSource = new DOMSource(doc);
             sr = new StreamResult(file);
             transformer.transform(domSource, sr);
         } catch (ParserConfigurationException pce){
             Log.e(TAG, "Ошибка создания файла истории: ", pce);
         } catch (TransformerConfigurationException tce) {
             Log.e(TAG, "Ошибка создания файла истории: ", tce);
         } catch (TransformerException e) {
             Log.e(TAG, "Ошибка создания файла истории: ", e);
         }
    }

    public void setUrl(String url, String nameSmall, String dateLoad){
        if (file.isFile() == false)
            createdocumentHistory();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(file);
            element = doc.getDocumentElement();
            Element childelement = doc.createElement("image");
            element.appendChild(childelement);

            Attr attr = doc.createAttribute("date");
            attr.setValue(dateLoad);
            childelement.setAttributeNode(attr);

            attr = doc.createAttribute("link");
            attr.setValue(url);
            childelement.setAttributeNode(attr);

            attr = doc.createAttribute("nameSmall");
            attr.setValue(nameSmall);
            childelement.setAttributeNode(attr);

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            domSource = new DOMSource(doc);
            sr = new StreamResult(file);
            transformer.transform(domSource, sr);
            Log.v(TAG, "История сохранена");
        } catch (TransformerConfigurationException tce){
            Log.e(TAG, "Ошибка сохранения истории: ", tce);
        } catch (TransformerException te){
            Log.e("Error", String.valueOf(te));
        } catch (ParserConfigurationException pce) {
            Log.e(TAG, "Ошибка сохранения истории: ", pce);
        } catch (Exception e){
            Log.e(TAG, "Ошибка сохранения истории: ", e);
        }
        //getUrl();
    }

    public List getUrl(String param){
        String url = null;
        String nameSmall = null;
        List list = new ArrayList();
        int indexList = 0;
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
                //nameSmall = getelement.getAttribute("nameSmall");
                File file1 = new File(Environment.getExternalStorageDirectory()+"/photos/"+getelement.getAttribute("nameSmall"));
                if (file1.isFile()){
                    if (param.equals("name")){
                        list.add(indexList, getelement.getAttribute("nameSmall"));
                        Log.v("APPLICATION","Аттрибур: " + getelement.getAttribute("nameSmall"));
                        indexList = indexList + 1;
                    }
                    else {
                        list.add(indexList, getelement.getAttribute("link"));
                        Log.v("APPLICATION","link: " + getelement.getAttribute("link"));
                        indexList = indexList + 1;
                    }
                }

            }
            Log.v(TAG, "Длина list (история): "+list.size());
        }
        catch (ParserConfigurationException pce) {
            Log.e("APPLICATION","ОШИБКА ПОЛУЧЕНИЯ АТТРИБУТА:",pce);
        }
        catch (NullPointerException npe){
            Log.e(TAG, "ОШИБКА ПОЛУЧЕНИЯ АТТРИБУТА:", npe);
        }
        catch (Exception e) {
            Log.e("APPLICATION","ОШИБКА ПОЛУЧЕНИЯ АТТРИБУТА:",e);
        }
        return list;
    }
}
