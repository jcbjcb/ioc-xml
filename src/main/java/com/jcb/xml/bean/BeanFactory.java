package com.jcb.xml.bean;

import java.util.*;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 20:17
 **/
public interface BeanFactory {
    /** 获取bean 利用反射调用无参的构造函数
     * @param className
     * @return
     */
    default Object getBean(String className) {
        return null;
    }

    default Object getBean(String className, Object... args) {
        return null;
    }

    /**
     * 根据类型生成collection 实现类
     * @param CollectionClazz
     * @param size
     * @return
     */
    public Collection<?> createCollection(String CollectionClazz,int size) throws ClassNotFoundException;
}

