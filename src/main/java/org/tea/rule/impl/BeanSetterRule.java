package org.tea.rule.impl;

import org.tea.rule.SetterRule;

/**
 * Java Bean 默认的 Setter 方法生成规则实现
 *
 * @author lxh
 */
public class BeanSetterRule implements SetterRule {

    @Override
    public String setterName(String fieldName) {
        if (fieldName == null || fieldName.trim().length() == 0) {
            throw new IllegalArgumentException("生成 Setter 方法时，对应的属性名不能为空");
        }
        if (Character.isUpperCase(fieldName.charAt(0))) {
            return "set" + fieldName;
        }
        int n = fieldName.length();
        if (n > 1 && Character.isUpperCase(fieldName.charAt(1))) {
            return "set" + fieldName;
        }
        if (fieldName.startsWith("is")) {
            return "set" + fieldName.substring(2);
        }
        return "set" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
    }
}
