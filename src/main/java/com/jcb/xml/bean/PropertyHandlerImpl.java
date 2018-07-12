package com.jcb.xml.bean;

import com.jcb.xml.util.IocUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: deu
 * @description: ${description}
 * @author: jcb
 * @create: 2018-07-10 20:50
 **/
public class PropertyHandlerImpl implements  PropertyHandler {
    @Override
    public Object setProperties(Object obj, Map<String, Object> properties) {
        Class<?> clazz= obj.getClass();
        properties.keySet().forEach(p->{
            Class<?> propertiesClass= IocUtil.getClass(properties.get(p));
            String setMethodName=this.getSetMenthodName(p);
            try {

//                Method method= clazz.getMethod(setMethodName,propertiesClass); 如果使用该方法 会不能获取以多态方式传入的参数
                Method method= this.getMethod(clazz.getTypeName(),setMethodName,propertiesClass);
                if(method == null){
                    throw new NoSuchMethodException();
                }
                method.invoke(obj,properties.get(p));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return obj;
    }

    /**
     * 获取propety 的set 函数
     * @param property
     * @return
     */
    private String getSetMenthodName(String property){
        StringBuilder sb =  new StringBuilder("set");
        String first=property.substring(0,1).toUpperCase();

        String second=property.substring(1);
        sb.append(first);
        sb.append(second);

        return  sb.toString();
    }

    /**
     * 获取所有方法
     * @param className
     * @return
     */
    private Method[] getMethods(String className){
        try {
            Class<?> clazz= Class.forName(className);
            return  clazz.getMethods();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  null;
    }

    /**
     * 获取只有一个参数的对应方法
     * @param className
     * @param methodName
     * @return
     */
    private Method getMethod(String className,String methodName,Class<?> propertiesClass){

        Method[] methods=getMethods(className);
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                if(method.getParameterCount() == 1){
                    //该函数的参数类型和传入参数类型相同
                    if(method.getParameterTypes()[0].getTypeName().equals(propertiesClass.getTypeName())){
                        return method;
                        //该函数的参数类型是传入参数类型的父类
                    }else if(method.getParameterTypes()[0].getTypeName().equals(propertiesClass.getSuperclass().getTypeName())){
                        return method;
                    }else
                    {
                        Set<String> superClassAndSuperInterfaceList= this.getAllSuperClassAndSuperInterface(propertiesClass);
                        //如果传入参数类型是参数类型的子类 也返回改函数
                        if(superClassAndSuperInterfaceList.contains(method.getParameterTypes()[0].getTypeName()))
                            return method;

                    }
                }
            }
        }
        return  null;
    }


    /**
     * 获取所有父类类型和父类接口类型
     * @param clazz
     * @return
     */
    private Set<String> getAllSuperClassAndSuperInterface(Class<?> clazz){
        Set<String> superClassAndSuperInterfaceList = new HashSet<>();
        getAllSupersClasss(superClassAndSuperInterfaceList,clazz);
        getAllSuperInterfaces(superClassAndSuperInterfaceList,clazz);
        return superClassAndSuperInterfaceList;
    }

    /**
     * 递归获取所父类 类型
     * @param parentClassList
     * @param clazz
     */
    private Set<String> getAllSupersClasss(Set<String> parentClassList,Class<?> clazz){
        parentClassList.add(clazz.getSuperclass().getName());
        if(Object.class.getTypeName()!=clazz.getSuperclass().getTypeName()){
            //父类也可能实现接口
            getAllSuperInterfaces(parentClassList,clazz.getSuperclass());
            //递归查询父类
            getAllSupersClasss(parentClassList,clazz.getSuperclass());
        }
        return parentClassList;
    }

    /**
     * 递归获取父类接口
     * @param parentInterfaceList
     * @param clazz
     */
    private Set<String> getAllSuperInterfaces(Set<String> parentInterfaceList,Class<?> clazz){
        for (Class<?> aClass : clazz.getInterfaces()) {
            parentInterfaceList.add(aClass.getTypeName());
            //递归查询实现接口
            getAllSuperInterfaces(parentInterfaceList,aClass);
        }
        return parentInterfaceList;
    }

}
