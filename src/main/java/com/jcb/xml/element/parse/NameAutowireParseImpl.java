package com.jcb.xml.element.parse;

public class NameAutowireParseImpl implements  AutowireParse {

    private String type;

    public NameAutowireParseImpl(String type) {
        this.type = type;
    }

    @Override
    public String getTYpe() {
        return type;
    }
}
