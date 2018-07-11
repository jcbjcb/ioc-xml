package com.jcb.xml.element.parse;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合型 元素
 */
public class ConllectionElement implements  LeafElementParse {

    private String value;

    private List<Element> list = new ArrayList<>();

    public ConllectionElement(String value) {
        this.value = value;
    }


    public List<Element> getList() {
        return list;
    }

    public void setList(List<Element> list) {
        this.list = list;
    }

    public void add(Element element){
        list.add(element);
    }

    @Override
    public String getVal() {
        return value;
    }


}
