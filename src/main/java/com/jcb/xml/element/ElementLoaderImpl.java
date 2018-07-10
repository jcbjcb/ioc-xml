package com.jcb.xml.element;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementLoaderImpl implements  ElementLoader {

    private Map<String ,Element> map = new HashMap<>();

    @Override
    public void addAllElement(Document document) {
        Element rootElement= document.getRootElement();
        List<Element> list= rootElement.elements();
        for (Element element:list) {
            String id = element.attributeValue("id");
            map.put(id,element);
        }
    }

    @Override
    public Element getElementById(String id) {
        return map.get(id);
    }

    @Override
    public Collection<Element> getAllElement() {
        return map.values();
    }
}
