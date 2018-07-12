package com.jcb.xml.util;

/**
 * ioc 工具类
 */
public class IocUtil {

    public static Class<?> getClass(Object obj){

        if(obj instanceof  Integer){
            return Integer.TYPE;
        }else if(obj instanceof  Long){
            return Long.TYPE;
        }else if(obj instanceof  Boolean){
            return Boolean.TYPE;
        }else if(obj instanceof  Short){
            return Short.TYPE;
        }else if(obj instanceof  Double){
            return Double.TYPE;
        }else if(obj instanceof  Float){
            return Float.TYPE;
        }else if(obj instanceof  Character){
            return Character.TYPE;
        }else if(obj instanceof  Byte){
            return Byte.TYPE;
        }

        return obj.getClass();

    }


    public static Object getValue(String className,String date){
        if(className.endsWith("Integer")){
            return Integer.parseInt(date);
        }else  if(className.endsWith("Long")){
            return Long.parseLong(date);
        }else  if(className.endsWith("Boolean")){
            return Boolean.parseBoolean(date);
        }else  if(className.endsWith("Short")){
            return Short.parseShort(date);
        }else  if(className.endsWith("Double")){
            return Double.parseDouble(date);
        }else  if(className.endsWith("Float")){
            return Float.parseFloat(date);
        }else  if(className.endsWith("Character")){
            return date.charAt(0);
        }else  if(className.endsWith("Byte")){
            return Byte.parseByte(date);
        }

        return date;
    }



}
