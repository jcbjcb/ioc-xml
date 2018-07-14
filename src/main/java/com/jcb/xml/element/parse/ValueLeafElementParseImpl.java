package com.jcb.xml.element.parse;

public class ValueLeafElementParseImpl implements  LeafElementParse{



    private Object value;

    public ValueLeafElementParseImpl(Object value) {
        this.value = value;
    }

    @Override
    public Object getVal() {
        return value;
    }
}
