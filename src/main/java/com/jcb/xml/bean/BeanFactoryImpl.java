package com.jcb.xml.bean;

import com.jcb.xml.util.IocUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
//            Constructor<?> constructor= clazz.getConstructor(argsClass);
            Constructor<?> constructor = getConstructor(clazz,argsClass);
            return constructor.newInstance(args);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException  e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建 collection
     * @param CollectionClazz
     * @param size
     * @return
     */
    @Override
    public Collection<?> createCollection(String CollectionClazz, int size) throws ClassNotFoundException {
        Class<?>  CollectionClazzType= Class.forName(CollectionClazz);
        if (CollectionClazzType.isInterface()) {
            if (Set.class == CollectionClazzType || Collection.class == CollectionClazzType) {
                return new LinkedHashSet<>(size);
            }
            else if (List.class == CollectionClazzType) {
                return new ArrayList<>(size);
            }
            else {
                return null;
            }
        }else{
            if (!Collection.class.isAssignableFrom(CollectionClazzType)) {
                return null;
            }
            try {
                return (Collection<?>) CollectionClazzType.newInstance();
            }
            catch (Throwable ex) {
                return null;
            }
        }
    }

    /**
     * 获取参数类型
     *
     */
    private Class<?>[] getArgsClass(Object... args){
        List<Class<?>> list = new ArrayList<>();
        for (Object arg : args) {
            list.add(IocUtil.getClass(arg));
        }
        return list.toArray(new Class[0]);
    }

    /**
     * 获取参数所对应的class构造函数
     * @param clazz
     * @param argsClass
     * @return
     */
    private Constructor<?> getConstructor(Class<?> clazz,Class<?>[] argsClass){
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if(constructor.getParameterTypes().length==argsClass.length){
                    if(checkArgsType(constructor.getParameterTypes(),argsClass)){
                        return constructor;
                    }
            }else{
                continue;
            }
        }

        return null;
    }

    /**
     *
     * @param constructorArgsClass 函数的参数类型
     * @param argsClass 传入函数的参数类型
     * @return
     */
    private boolean checkArgsType(Class<?>[] constructorArgsClass,Class<?>[] argsClass){

        for (int i =0;i< constructorArgsClass.length;i++) {
            //aClass.isAssignableFrom(bClass)  判断a是否是b的父类或则父接口
                if(!constructorArgsClass[i].isAssignableFrom(argsClass[i]))
                    return false;

        }

        return true;

    }
}
