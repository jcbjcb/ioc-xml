package com.jcb.xml.document;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-07 17:27
 **/
public class XmlEntityresolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        /**
         * publicId: systemId已经可以表示任何位置的外部DTD资源了，但是它是直接指向相应的资源，
         * publicId的作用在于其间接性。publicID就相当于一个名字，这个名字代表了一个外部资源。
         * 比如，我们规定”W3C HTML 4.01″这个字符串对应”http://www.w3.org/somedir/somefile.dtd”这个资源。
         * 那么，publicID=”W3C HTML 4.01″ 和 systemID=”http://www.w3.org/somedir/somefile.dtd”是一样的，
         * 二者都引用了http://www.w3.org/somedir/somefile.dtd作为该文档的外部DTD。
         */
        try {
            InputStream inputStream=new FileInputStream(new File(this.getClass().
                    getResource("/").getPath().toString().substring(1)+"beans.dtd"));
            return new InputSource(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
