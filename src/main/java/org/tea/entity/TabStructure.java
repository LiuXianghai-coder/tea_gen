package org.tea.entity;

import org.tea.annotation.Column;

/**
 * 表结构中，表的每一列对应的相关属性
 *
 * @author lxh
 * @date 2022/6/5-下午9:05
 */
public abstract class TabStructure {
    @Column(name="table_name")
    private String tableName;

    @Column(name="column_name")
    private String columnName;

    @Column(name="data_type")
    private String dataType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public abstract String getColComment();
}
