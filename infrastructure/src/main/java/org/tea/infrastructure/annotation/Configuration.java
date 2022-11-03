package org.tea.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 标记一个类，表示当前类是一个配置类
 *
 * @author xhliu
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Configuration {
    /**
     * 指定组件类的名称
     */
    String value() default "";
}
