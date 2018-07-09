package com.jcb.xml.document;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.File;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-07 16:53
 **/
public class XmlDocumentLoader implements  DocumentLoader {

    @Override
    public Document loadDocument(String filepath) throws SAXException {
        File file = new File(filepath);

        SAXReader saxReader = new SAXReader();
        saxReader.setValidation(true);
        saxReader.setEntityResolver(new XmlEntityresolver());
        try {
            Document document= saxReader.read(file);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
