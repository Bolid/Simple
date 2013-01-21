package com.sample.httpExample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class myParser extends DefaultHandler{
	String url = "";
	String userName = "";
	String title = "";
	/*public myParser(String parURL) {
		this.url = parURL;
	}*/
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) 
	throws SAXException{
		try{
			if (localName.equalsIgnoreCase("img"))
				if (attributes.getValue(2).toString().equals("XL"))
				{
					url = attributes.getValue(1).toString();
					}
			if (localName.equalsIgnoreCase("name"))
				userName = "1";
			if (qName.equalsIgnoreCase("entry"))
				title = "0";
			if (title.equals("0") & localName.equals("title"))
				title = "1";			
		}
		catch (Exception e) {
			
		}
	}
	public void endElement (String namespaseURI, String localName, String qName) throws SAXException{	
	}
	public void characters (char[] ch, int start, int length) throws SAXException{
		String cdate = new String(ch, start, length);
		{
			if (userName.equals("1"))
				userName = cdate;
			if (title.equals("1"))
				title = cdate;
			}
	}

}
