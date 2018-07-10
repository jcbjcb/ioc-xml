package com.jcb.xml.element.parse;

public class ValueLeafElementParseImpl implements  LeafElementParse{



    private String value;

    public ValueLeafElementParseImpl(String value) {
        this.value = value;
    }

    @Override
    public String getVal() {
        return value;
    }
}
