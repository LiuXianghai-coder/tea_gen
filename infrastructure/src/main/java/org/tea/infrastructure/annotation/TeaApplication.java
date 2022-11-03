package org.tea.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 用于标记对应的应用程序，使得能够将相关的以来项管理交给内置的 IOC 来完成
 * @author xhliu
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
public @interface TeaApplication {
}
