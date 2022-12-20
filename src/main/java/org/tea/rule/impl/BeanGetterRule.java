package org.tea.rule.impl;

import org.tea.rule.GetterRule;

/**
 * JavaBean 默认的 Getter 生成实现类
 *
 * @author lxh
 */
public class BeanGetterRule implements GetterRule {

    @Override
    public String getterName(String filedName) {
        if (filedName == null || filedName.trim().length() == 0) {
            throw new IllegalArgumentException("生成的 Getter 方法属性名不能为空");
        }
        if (Character.isUpperCase(filedName.charAt(0))) {
            return "get" + filedName;
        }
        int n = filedName.length();
        if (n > 1 && Character.isUpperCase(filedName.charAt(1))) {
            return "get" + filedName;
        }
        if (filedName.startsWith("is")) {
            return filedName;
        }
        return "get" + Character.toUpperCase(filedName.charAt(0))
                + filedName.substring(1);
    }
}
