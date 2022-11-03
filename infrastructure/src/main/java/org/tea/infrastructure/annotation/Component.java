package org.tea.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记一个类，表示这个类是 IOC 容器中的一个组件类
 *
 * @author xhliu
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    /**
     * 指定组件类的名称
     */
    String value() default "";
}
