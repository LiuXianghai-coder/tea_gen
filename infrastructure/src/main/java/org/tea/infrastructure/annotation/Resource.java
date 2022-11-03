package org.tea.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记一个类中的字段，表示这个字段需要由 IOC 注入对应的依赖项 <br />
 *
 * @author xhliu
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {
    /**
     * 指定需要注入的 Bean 的名称，注入时会优先考虑通过 Bean 找到需要的依赖项
     */
    String name() default "";
}
