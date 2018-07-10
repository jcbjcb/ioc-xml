package com.jcb.xml.element;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;

/**
 * 加载beans 下面的所有bean
 */
public interface ElementLoader {

    void addAllElement(Document document);

    Element getElementById(String id);

    Collection<Element> getAllElement();

}
