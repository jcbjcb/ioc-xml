package com.jcb.xml.element.parse;

/**
 * 叶子元素
 */
public interface LeafElementParse {

    public default Object getVal() {
        return null;
    }

}
