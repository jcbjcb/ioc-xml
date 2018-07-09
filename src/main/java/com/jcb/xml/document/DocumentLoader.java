package com.jcb.xml.document;

import org.dom4j.Document;
import org.xml.sax.SAXException;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-07 16:45
 **/
public interface DocumentLoader {

    /**
     * @Description:
     * @Param: [path]
     * @return: org.dom4j.Document
     * @Author: jcb
     * @Date: 2018/7/7
     */
    public default Document loadDocument(String filepath) throws SAXException {
        return null;
    }
}
