package com.jcb.xml.context;

public class ApplicationContextImpl extends AbstractApplicationContext {

    public ApplicationContextImpl(String... xmlPath){
        initElement(xmlPath);
        instanceBean();

    }

}
