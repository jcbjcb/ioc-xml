package com.jcb.xml.bean;

import java.util.Map;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 20:48
 **/
public interface PropertyHandler {

    default Object setProperties(Object obj, Map<String, Object> properties) {
        return obj;
    }


}
