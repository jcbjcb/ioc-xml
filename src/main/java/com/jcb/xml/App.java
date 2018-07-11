package com.jcb.xml;

import com.jcb.xml.context.ApplicationContext;
import com.jcb.xml.context.ApplicationContextImpl;
import com.jcb.xml.model.Classes;
import com.jcb.xml.model.School;
import com.jcb.xml.model.Student;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SAXException {
//        DocumentLoader xmlDocumentHolder = new XmlDocumentLoader();
//        Document document= xmlDocumentHolder.loadDocument(xmlDocumentHolder.getClass().getResource("/").getPath()+"test.xml");
//
//        ElementLoader elementLoader = new ElementLoaderImpl();
//        elementLoader.addAllElement(document);
//        Element element = elementLoader.getElementById("test");
//
//        BeanElementParse beanElementParse = new BeanElementParseImpl();
//        String clazz= beanElementParse.getAttribute(element,"class");
//        System.out.println(clazz);
//
//        AutowireParse autowireParse= beanElementParse.getAutowire(element);
//
//        System.out.println(autowireParse.getTYpe());
//
//       List<Element> list= beanElementParse.getConstructorArgElement(element);
//       list.forEach(p-> System.out.println(p.elements().size()));
//
//
//       boolean singleton= beanElementParse.getSingleton(element);
//
//        System.out.println(singleton);
//
//        beanElementParse.getPropretyElement(element).forEach(p->{
//            p.elements().forEach(pp->{
//                BeanElementParse beanElementParse1 = new BeanElementParseImpl();
//                Object obj= beanElementParse1.getValueOfValueElement((Element) pp);
//                System.out.println("obj："+obj);
//            });
//        });

        test();

//        try {
//            Class<?> clazz= Class.forName(Student.class.getName());
//            Object student= clazz.newInstance();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
    }

    public static  void test(){
        String xmlpaht= App.class.getResource("/").getPath()+"test.xml";
        ApplicationContext applicationContext = new  ApplicationContextImpl(xmlpaht);
        School school = (School) applicationContext.getbyId("school");
        System.out.println(school.getName());

        Classes classes = (Classes) applicationContext.getbyId("classes");

        System.out.println(classes.getName());
        System.out.println(classes.getSchool().getName());
        classes.getSchool().setName("环球中心学校2");

        Student student = (Student) applicationContext.getbyId("student");
        System.out.println(student.getClasses().getSchool().getName());
    }

}
