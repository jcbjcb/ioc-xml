package com.jcb.xml.context;

import com.jcb.xml.bean.BeanFactory;
import com.jcb.xml.bean.BeanFactoryImpl;
import com.jcb.xml.bean.PropertyHandler;
import com.jcb.xml.bean.PropertyHandlerImpl;
import com.jcb.xml.document.DocumentLoader;
import com.jcb.xml.document.XmlDocumentLoader;
import com.jcb.xml.element.ElementLoader;
import com.jcb.xml.element.ElementLoaderImpl;
import com.jcb.xml.element.parse.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import java.util.*;

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
     * bean element工具类
     */
    BeanElementParse beanElementParse = new BeanElementParseImpl();
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
        Object bean= beanInstances.get(id);
        if(bean==null){
            //创建bean
            bean = createBean(id);
        }
        return bean;
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
        elements.stream().filter(element ->!beanElementParse.getLazyInit(element))
                .forEach(element -> {
                    String id= beanElementParse.getID(element);
                    String clazz=beanElementParse.getClazz(element);
                    Object bean= beanInstances.get(id);
                    if(bean==null){
                        //创建bean
                        createBean(id);
                    }


        });

    }

    /**
     * 创建bean
     * @param id
     */
    protected  Object createBean(String id){
        Element element= elementLoader.getElementById(id);
        List<Object> constructArgs= new ArrayList<>();
        beanElementParse.getConstructorArgElement(element).forEach(constructor->{
            constructor.elements().forEach(leafElement->{
                constructArgs.addAll(this.getArgs((Element) leafElement));
            });
        });

        String className= beanElementParse.getClazz(element);
        Object bean=null;
        if(constructArgs.size()>0)
             bean= beanFactory.getBean(className,constructArgs.toArray());
        else
             bean= beanFactory.getBean(className);
        //初始化参数
        bean=this.propertiesGHandler(bean,id);

        if(beanElementParse.getSingleton(element)){
            beanInstances.put(id,bean);
        }
        return  bean;

    }

    /**
     * 初始化bean属性
     * @param obj
     * @param id
     */
    protected Object propertiesGHandler(Object obj,String id){
        Element element= elementLoader.getElementById(id);
        Map<String,Object> properties = new HashMap<>();

        beanElementParse.getPropretyElement(element).forEach(property->{

            ((Element)property).elements().forEach(leafElementParse->{
                if(!beanElementParse.validateCollection((Element) leafElementParse))
                    properties.put(beanElementParse.getAttribute(property,"name") ,this.getOneArgs((Element) leafElementParse));
            });

            List<ConllectionElement> conllectionElements =beanElementParse.getConllectionElement(property);
            conllectionElements.forEach(conllectionElement -> {
                try {
                    Collection conllectionArgs = beanFactory.createCollection(conllectionElement.getVal(),conllectionElement.getList().size());
                    if(conllectionArgs!=null) {
                        conllectionElement.getList().forEach(leafElementParse -> {
                            conllectionArgs.add(this.getOneArgs((Element) leafElementParse));
                        });

                        properties.put(beanElementParse.getAttribute(property, "name"), conllectionArgs);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });


        });

        return propertyHandler.setProperties(obj,properties);
    }

    /**
     * 初始化参数列表
     * @param leafElement
     * @return
     */
    protected  List getArgs(Element leafElement){
        List<Object> argsList = new ArrayList<>();
        LeafElementParse leafElementParse= beanElementParse.getleafElement((Element) leafElement);
        if(leafElementParse instanceof RefLeafElementarseImpl){
            Object bean= beanInstances.get(leafElementParse.getVal());
            if(bean==null){
                //创建bean
                argsList.add(createBean(leafElementParse.getVal().toString()));
            }else{
                argsList.add(bean);
            }

        }else{
            argsList.add(leafElementParse.getVal());
        }
        return argsList;
    }

    /**
     * 返回只有一个参数
     * @param leafElement
     * @return
     */
    protected Object getOneArgs(Element leafElement){
        LeafElementParse leafElementParse= beanElementParse.getleafElement((Element) leafElement);
        if(leafElementParse instanceof RefLeafElementarseImpl){
            Object bean= beanInstances.get(leafElementParse.getVal());
            if(bean==null){
                //创建bean
               return createBean(leafElementParse.getVal().toString());
            }else{
                return bean;
            }

        }else{
            return leafElementParse.getVal();
        }
    }
}
