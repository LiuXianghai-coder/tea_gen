package org.tea.service;

import org.tea.entity.SchemaStructure;

import java.util.List;

/**
 * 用于获取指定数据库的所有公共表信息的统一服务类的接口
 */
public interface TableInfoService {
    /**
     * 查询制定数据库下所有的<b>公共的</b>表的名称列表
     *
     * @param dbName 待查询的数据库对象名
     * @return 查询到的数据库公共表名列表
     */
    List<SchemaStructure> selectAllTables(String dbName);
}
