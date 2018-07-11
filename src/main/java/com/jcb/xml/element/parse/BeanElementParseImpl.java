package com.jcb.xml.element.parse;

import com.jcb.xml.util.IocUtil;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class BeanElementParseImpl implements  BeanElementParse {

    @Override
    public List<Element> getConstructorArgElement(Element element) {
        return element.elements("constructor-arg");
    }

    @Override
    public List<Element> getPropretyElement(Element element) {
        return element.elements("property");
    }

    @Override
    public String getID(Element element) {
        return element.attributeValue("id");
    }

    @Override
    public String getClazz(Element element) {
        return element.attributeValue("class");
    }

    @Override
    public boolean getLazyInit(Element element) {
        String lazyInit=element.attributeValue("lazy-init");
        if("defaule".equals(lazyInit) || "false".equals(lazyInit)){
            return false;
        }
        return true;
    }

    @Override
    public boolean getSingleton(Element element) {
        String singleton=element.attributeValue("singleton");
        if("false".equals(singleton)){
            return false;
        }
        return true;
    }

    @Override
    public AutowireParse getAutowire(Element element) {
        String  autowire= element.attributeValue("autowire");
        if("no".equals(autowire)){
            return new NoAutowireParseImpl(autowire);
        }else if("byName".equals(autowire)){
            return new NameAutowireParseImpl(autowire);
        }

        return new NoAutowireParseImpl(autowire);
    }

    @Override
    public String getAttribute(Element element, String name) {
        return element.attributeValue(name);
    }

    @Override
    public LeafElementParse getleafElement(Element element) {
        String name = element.getName();
        if("ref".equals(name)){
            return  new RefLeafElementarseImpl(element.attributeValue("bean"));
        }else if("value".equals(name)){
            return new ValueLeafElementParseImpl(element.attributeValue("type"));
        }
        return null;
    }

    @Override
    public ConllectionElement getConllectionElement(Element element) {

        Element conllectionElement= element.element("collection");
        if(conllectionElement!=null) {
            ConllectionElement ConllectionElement = new ConllectionElement(conllectionElement.attributeValue("type"));
            conllectionElement.elements().forEach(leafEnelment -> ConllectionElement.add(this.getleafElement((Element) leafEnelment)));
            return ConllectionElement;
        }
        return  null;
    }

    @Override
    public Object getValueOfValueElement(Element leafElement) {

        String type = leafElement.attributeValue("type");
        String date = leafElement.getText();

        return IocUtil.getValue(type,date);
    }

    @Override
    public List<LeafElementParse> getConstructorArgsValue(Element element) {
        List<LeafElementParse> list = new ArrayList<>();

        this.getConstructorArgElement(element).forEach(p->{
            ((Element) p).elements().forEach(leaf->{
                LeafElementParse leafElement= this.getleafElement((Element) leaf);
                list.add(leafElement);
            });
        });
        return list;
    }
}
