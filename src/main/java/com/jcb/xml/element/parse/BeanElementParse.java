package com.jcb.xml.element.parse;

import org.dom4j.Element;

import java.util.List;

/**
 * 解析bean 下面的子元素
 */
public interface BeanElementParse {


    /**
     * 获取constructorarg 列表
     * @param element
     * @return
     */
    public List<Element> getConstructorArgElement(Element element);

    /**
     * 获取proprety 列表
     * @param element
     * @return
     */
    public List<Element> getPropretyElement(Element element);


    public String getID(Element element);

    public String getClazz(Element element);

    /**
     * 获取延迟加载
     * @param element
     * @return
     */
    public boolean getLazyInit(Element element);

    /**
     * 获取是否单例
     * @param element
     * @return
     */
    public boolean getSingleton(Element element);

    /**
     * 获取注入方式
     * @param element
     * @return
     */
    public AutowireParse getAutowire(Element element);

    /**
     * 获取属性值
     * @param element
     * @param name
     * @return
     */
    public String getAttribute(Element element, String name);

    /**
     * 获取叶子节点 ref 或 value
     * @param element
     * @return
     */
    public LeafElementParse getleafElement(Element element);


    /**
     * 获取集合节点
     * @param element
     * @return
     */
    public List<ConllectionElement> getConllectionElement(Element element);


    /**
     *  转换叶子节点值
     * @param leafElement
     * @return
     */
    public Object getValueOfValueElement(Element leafElement);


    /**
     * 获取constructor 参数值
     * @param element
     * @return
     */
    public List<LeafElementParse> getConstructorArgsValue(Element element);


    /**
     * 检查是否是collection
     * @param element
     * @return
     */
    public boolean validateCollection(Element element);



}
