package com.jcb.xml;

import com.jcb.xml.document.XmlDocumentLoader;
import org.xml.sax.SAXException;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) throws SAXException {
        XmlDocumentLoader xmlDocumentLoader = new XmlDocumentLoader();
        System.out.println(xmlDocumentLoader.getClass().getResource("/").getPath().substring(1)+"test.xml");
        xmlDocumentLoader.loadDocument(xmlDocumentLoader.getClass().getResource("/").getPath().substring(1)+"test.xml");
    }
}
