package org.tea.service;

import org.tea.entity.TabStructure;

/**
 * 和父类属性进行处理的统一接口
 *
 * @author lxh
 * @date 2022/7/24-下午1:07
 */
public interface SuperClassService {
    /**
     * 检查当前处理的列结构是否需要生成到对应的实体类中，由于这里可能存在抽象父类的
     * 原因，因此实际上子类不一定需要生成相关的实例属性，而是直接复用父类的相关属性
     *
     * @param struct    对应的列结构
     * @param sc    生成的目标实体类的父类
     * @return  是否需要将当前的列生成到目标实体类中
     */
    boolean willGen(TabStructure  struct, Class<?> sc);
}
