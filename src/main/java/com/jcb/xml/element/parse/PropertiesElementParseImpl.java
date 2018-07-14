package com.jcb.xml.element.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-14 11:59
 **/
public class PropertiesElementParseImpl implements  LeafElementParse {

    private String val;

    private List<LeafElementParse> list = new ArrayList<>();

    public PropertiesElementParseImpl(String val) {
        this.val = val;
    }

    public List<LeafElementParse> getList() {
        return list;
    }

    public void setList(List<LeafElementParse> list) {
        this.list = list;
    }

    @Override
    public String getVal() {
        return null;
    }
}
