package com.jcb.xml.element.parse;

public class RefLeafElementarseImpl implements  LeafElementParse {

    private String value;

    public RefLeafElementarseImpl(String value) {

        this.value = value;
    }

    @Override
    public String getVal() {
        return value;
    }
}
