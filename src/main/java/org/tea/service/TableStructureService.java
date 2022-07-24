package org.tea.service;

import org.tea.domain.psql.entity.PsqlTableStructure;
import org.tea.entity.TabStructure;

import java.util.List;

/**
 * 查询相关表结构信息的统一接口
 *
 * @author lxh
 * @date 2022/6/4-下午9:23
 */
public interface TableStructureService {
    /**
     * 通过表名来查询相关表的结构信息
     *
     * @param dbName 表所在的数据库
     * @param tableName 待查询的表名
     * @return  查询到的表的结构信息列表
     */
    List<TabStructure> selectByTableName(String dbName, String tableName);

    /**
     * 过滤掉那些在父类中已经存在的相关属性
     *
     * @param structs   相关的列结构列表
     * @param clazz 相关的直接父类
     * @return  经过过滤之后的，待生成到目标实体类的列列表
     */
    List<TabStructure> filterColumns(List<TabStructure> structs, Class<?> clazz);
}
