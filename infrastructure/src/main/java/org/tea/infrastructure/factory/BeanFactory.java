package org.tea.infrastructure.factory;

import org.tea.infrastructure.exception.NoSuchBeanException;
import org.tea.infrastructure.exception.TooManyMatchedBeanException;

/**
 * Bean 工厂接口，定义创建势力 Bean 的相关逻辑 （抽象工厂模式）
 *
 * @author lxh
 */
public interface BeanFactory {
    /**
     * 通过指定的 Bean 名称，从当前的 Bean 工厂中获取已经创建好的 Bean 实例，
     * 如果当前的 Bean 工厂中不存在已经创建好的实例，那么对应的子类实现应当能够创建对应的 Bean 实例 <br />
     * 如果 Bean 工厂不能通过指定的 Bean 名称创建对应的 Bean 实例，那么应当抛出 {@link NoSuchBeanException}
     * @param name  指定的 Bean 的名称，在 Bean 的生命周期中它是唯一的
     * @return  通过指定的 Bean 名称创建的 Bean 实例对象
     * @throws NoSuchBeanException 对应 Bean 工厂无法创建对应实例时抛出
     */
    Object getBean(String name);

    /**
     * 创建 Bean 时可能需要涉及到 Bean 对应的对象的实例化，而实例化对象有时必须需要相关参数，因此这个方法
     * 给与对应的能力去实例化这些需要参数的 Bean 对象 <br/>
     *
     * @param name  指定的 Bean 名称，在 Bean 的生命周期中它是唯一的
     * @param args  实例化制定 Bean 对象需要的相关参数
     * @return  通过指定的 BeanName 和参数构造的 Bean 实例对象
     * @throws NoSuchBeanException 当对应 Bean 工厂无法创建对应实例时抛出
     */
    Object getBean(String name, Object... args);

    /**
     * 通过给定的 Bean 名称和 Bean 类型来创建对应的 Bean 实例
     * @param name  指定的 Bean 名称
     * @param clazz 要创建的 Bean 的所属类型
     * @return  结合 Bean 类型和名称所创建的 Bean 实例
     * @throws NoSuchBeanException 当指定的 Bean 名称不存在时抛出
     */
    Object getBean(String name, Class<?> clazz);

    /**
     * 通过指定的 Bean 的类型来创建对应的 Bean 实例对象，由于在一个 Bean 工厂中可能存在多个类型一致的候选 Bean，
     * 在这种情况下应当抛出 {@link TooManyMatchedBeanException} <br />
     * @param clazz 需要创建的 Bean 的类型
     * @return  如果给定的 Bean 的类型在 Bean 工厂中是唯一的，那么将会返回对应的实例 Bean
     * @throws NoSuchBeanException  如果给定的 Bean 类型在 Bean 工厂中不存在则抛出
     * @throws TooManyMatchedBeanException 当给定的 Bean 在 Bean 工厂中不是唯一的 Bean 实例时抛出
     */
    Object getBean(Class<?> clazz);

    /**
     * 同 {@link #getBean(Class)}，因为创建 Bean 实例对象时可能需要相关的参数，此方法即提供对应的选项
     * @param clazz 要创建的 Bean 的类型
     * @param args  构造 Bean 实例时需要的参数
     * @return  如果构造成功，则返回构造的 Bean 实例对象
     * @throws NoSuchBeanException 如果对应的 Bean 工厂无法创建这样类型的 Bean 时抛出
     * @throws TooManyMatchedBeanException  当给定的 Bean 类型和参数都在 Bean 工厂中有多个实例时抛出
     */
    Object getBean(Class<?> clazz, Object... args);

    /**
     * 检查当前的 Bean 工厂中是否已经存在给定 Bean 名称的 Bean, 或者如果 Bean 工厂能够创建指定 Bean 名称的 Bean 时，
     * 返回 {@code true}, 否则，实现类应当返回 {@code false}
     * @param name  相关的 Bean 的名称
     * @return  如果能够创建给定 Bean，返回 {@code true}，否则返回 {@code false}
     */
    boolean containsBean(String name);
}
