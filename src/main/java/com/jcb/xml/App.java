package com.jcb.xml;

import com.jcb.xml.document.DocumentLoader;
import com.jcb.xml.document.XmlDocumentLoader;
import com.jcb.xml.element.ElementLoader;
import com.jcb.xml.element.ElementLoaderImpl;
import com.jcb.xml.element.parse.AutowireParse;
import com.jcb.xml.element.parse.BeanElementParse;
import com.jcb.xml.element.parse.BeanElementParseImpl;
import org.dom4j.Document;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SAXException {
        DocumentLoader xmlDocumentHolder = new XmlDocumentLoader();
        Document document= xmlDocumentHolder.loadDocument(xmlDocumentHolder.getClass().getResource("/").getPath()+"test.xml");

        ElementLoader elementLoader = new ElementLoaderImpl();
        elementLoader.addAllElement(document);
        Element element = elementLoader.getElementById("test");

        BeanElementParse beanElementParse = new BeanElementParseImpl();
        String clazz= beanElementParse.getAttribute(element,"class");
        System.out.println(clazz);

        AutowireParse autowireParse= beanElementParse.getAutowire(element);

        System.out.println(autowireParse.getTYpe());

       List<Element> list= beanElementParse.getConstructorArgElement(element);
       list.forEach(p-> System.out.println(p.elements().size()));


       boolean singleton= beanElementParse.getSingleton(element);

        System.out.println(singleton);

        beanElementParse.getPropretyElement(element).forEach(p->{
            p.elements().forEach(pp->{
                BeanElementParse beanElementParse1 = new BeanElementParseImpl();
                Object obj= beanElementParse1.getValueOfValueElement((Element) pp);
                System.out.println("obj："+obj);
            });
        });


    }

}
