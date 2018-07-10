package com.jcb.xml.element.parse;

public class ConstrucotrArgParseImpl implements ConstructorArgParse {

    private String value;

    public ConstrucotrArgParseImpl(String value) {
        this.value = value;
    }

    @Override
    public String getVal() {
        return value;
    }
}
