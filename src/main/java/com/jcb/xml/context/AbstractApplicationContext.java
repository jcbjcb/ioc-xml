package com.jcb.xml.context;

import com.jcb.xml.bean.BeanFactory;
import com.jcb.xml.bean.BeanFactoryImpl;
import com.jcb.xml.bean.PropertyHandler;
import com.jcb.xml.bean.PropertyHandlerImpl;
import com.jcb.xml.document.DocumentLoader;
import com.jcb.xml.document.XmlDocumentLoader;
import com.jcb.xml.element.ElementLoader;
import com.jcb.xml.element.ElementLoaderImpl;
import com.jcb.xml.element.parse.BeanElementParse;
import com.jcb.xml.element.parse.BeanElementParseImpl;
import org.dom4j.Document;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 22:19
 **/
public abstract  class AbstractApplicationContext implements ApplicationContext{

    /**
     * xml加载器
     */
    protected DocumentLoader documentLoader = new XmlDocumentLoader();
    /**
     * element 加载器
     */
    protected ElementLoader elementLoader = new ElementLoaderImpl();
    /**
     * bean工厂
     */
    protected BeanFactory beanFactory =new  BeanFactoryImpl();

    /**
     * 属性处理器
     */
    protected PropertyHandler propertyHandler = new PropertyHandlerImpl();

    /**
     * 缓存的单例对象
     *
     */
    protected Map<String,Object> beanInstances = new HashMap<>();

    @Override
    public Object getbyId(String id) {
        return beanInstances.get(id);
    }

    /**
     * 初始化xml 文件
     * @param xmlPath
     */
    protected  void initElement(String[] xmlPath){
        for (String path : xmlPath) {
            try {
                Document document=documentLoader.loadDocument(path);
                elementLoader.addAllElement(document);
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 初始化所有bean 延迟加载的不创建实例
     */
    protected void instanceBean(){
         Collection<Element> elements= elementLoader.getAllElement();
        BeanElementParse beanElementParse = new BeanElementParseImpl();
        elements.stream().filter(element ->!beanElementParse.getLazyInit(element))
                .forEach(element -> {
                    String id= beanElementParse.getID(element);
                    String clazz=beanElementParse.getClazz(element);
                    //TODO 获取所有够着函数的参数
                     beanElementParse.getConstructorArgElement(element);


        });

    }
}
