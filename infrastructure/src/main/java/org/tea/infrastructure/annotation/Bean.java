package org.tea.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记一个方法，表示这个方法返回的实例将会作为 IOC 中的单例 Bean，其它依赖于
 * 此 Bean 的组件可以通过相关的配置引入需要的 Bean 依赖
 *
 * @author xhliu
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
    /**
     * 指定当前标记的 Bean 的名称
     */
    String name() default "";
}
