package com.jcb.xml.bean;

import com.jcb.xml.util.IocUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 20:24
 **/
public class BeanFactoryImpl implements  BeanFactory {

    @Override
    public Object getBean(String className) {
        try {
            Class<?> clazz= Class.forName(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getBean(String className, Object... args) {
        Class<?>[] argsClass= this.getArgsClass(args);
        try {
            Class<?> clazz= Class.forName(className);
            Constructor<?> constructor= clazz.getConstructor(argsClass);
            return constructor.newInstance(args);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?>[] getArgsClass(Object... args){
        List<Class<?>> list = new ArrayList<>();
        for (Object arg : args) {
            list.add(IocUtil.getClass(arg));
        }
        return list.toArray(new Class[0]);
    }
}
