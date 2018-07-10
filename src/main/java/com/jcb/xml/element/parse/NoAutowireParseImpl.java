package com.jcb.xml.element.parse;

public class NoAutowireParseImpl implements  AutowireParse {


    private String type;

    public NoAutowireParseImpl(String type) {
        this.type = type;
    }


    @Override
    public String getTYpe() {
        return "no";
    }
}
