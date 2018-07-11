package com.jcb.xml.element.parse;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合型 元素
 */
public class ConllectionElement implements  LeafElementParse {

    private String value;

    private List<LeafElementParse> list = new ArrayList<>();

    public ConllectionElement(String value) {
        this.value = value;
    }


    public List<LeafElementParse> getList() {
        return list;
    }

    public void setList(List<LeafElementParse> list) {
        this.list = list;
    }

    public void add(LeafElementParse element){
        list.add(element);
    }

    @Override
    public String getVal() {
        return value;
    }


    public Object[] getvalues(){
        List<Object> values = new ArrayList<>();
        list.stream().forEach(p->{
            values.add(p.getVal());
        });
        return values.toArray();
    }
}
