package org.tea.annotation;

import java.lang.annotation.*;

/**
 * 对应的实体类中，相关的属性字段和数据库表字段对应时，
 * 可以加上此注解来实现对应的实体类的属性和数据库表字段之间的映射关系
 *
 * @author lxh
 * @date 2022/6/4-下午5:35
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    /**
     * 实体类属性对应的数据库表的字段名
     */
    String name();
}
