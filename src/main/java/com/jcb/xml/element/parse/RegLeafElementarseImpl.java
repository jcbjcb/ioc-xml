package com.jcb.xml.element.parse;

public class RegLeafElementarseImpl implements  LeafElementParse {

    private String value;

    public RegLeafElementarseImpl(String value) {

        this.value = value;
    }

    @Override
    public String getVal() {
        return value;
    }
}
