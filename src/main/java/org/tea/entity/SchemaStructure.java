package org.tea.entity;

import org.tea.annotation.Column;

/**
 * 数据库中，用于记录每个表相关信息的抽象实体类
 *
 * @author lxh
 * @date 2022/6/5-下午9:06
 */
public abstract class SchemaStructure {
    @Column(name="table_name")
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public abstract String getSchemaComment();
}
