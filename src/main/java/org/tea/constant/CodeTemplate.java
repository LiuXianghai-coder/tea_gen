package org.tea.constant;

/**
 * 有关代码模板相关的常量
 *
 * @author lxh
 * @date 2022/6/5-下午9:42
 */
public class CodeTemplate {
    public final static String XML_TEMPLATE = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
            "<!DOCTYPE mapper\n" +
            "        PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n" +
            "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
            "<mapper namespace=\"#\">\n";

    public final static String RESULT_XML_TEMP = "\t<resultMap id=\"BaseResultMap\" type=\"#\">\n";

    public final static String EQUALS_METHOD_TMP = "\t@Override\n\tpublic boolean equals(Object o) {\n"
            + "\t\tif (this == o) return true;\n"
            + "\t\tif (o == null || getClass() != o.getClass()) return false;";

    public final static String HASH_CODE_TMP = "\t@Override\n\tpublic int hashCode(){\n";
}
