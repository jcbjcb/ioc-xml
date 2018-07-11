package com.jcb.xml.bean;

import com.jcb.xml.util.IocUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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
                ///TODO 获取setList 方法有问题   java.lang.NoSuchMethodException: com.jcb.xml.model.Student.setList(java.util.ArrayList)
                Method method= clazz.getMethod(setMethodName,propertiesClass);
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


}
