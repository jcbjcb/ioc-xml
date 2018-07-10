package com.jcb.xml.context;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 22:12
 **/
public interface ApplicationContext {

    /**
     * 通过id获取bean
     * @param id
     * @return
     */
    public Object getbyId(String id);


}
