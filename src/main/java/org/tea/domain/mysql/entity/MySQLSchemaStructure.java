package org.tea.domain.mysql.entity;


import org.tea.annotation.Column;
import org.tea.entity.SchemaStructure;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author lxh
 * @date 2022/6/5-下午8:54
 */
public class MySQLSchemaStructure extends SchemaStructure {
    @Column(name="TABLE_CATALOG")
    private String tableCatalog;

    @Column(name="TABLE_SCHEMA")
    private String tableSchema;

    @Column(name="TABLE_TYPE")
    private String tableType;

    @Column(name="ENGINE")
    private String engine;

    @Column(name="VERSION")
    private long version;

    @Column(name="ROW_FORMAT")
    private String rowFormat;

    @Column(name="TABLE_ROWS")
    private BigInteger tableRows;

    @Column(name="AVG_ROW_LENGTH")
    private BigInteger avgRowLength;

    @Column(name="DATA_LENGTH")
    private BigInteger dataLength;

    @Column(name="MAX_DATA_LENGTH")
    private BigInteger maxDataLength;

    @Column(name="INDEX_LENGTH")
    private BigInteger indexLength;

    @Column(name="DATA_FREE")
    private BigInteger dataFree;

    @Column(name="AUTO_INCREMENT")
    private BigInteger autoIncrement;

    @Column(name="CREATE_TIME")
    private Timestamp createTime;

    @Column(name="UPDATE_TIME")
    private LocalDateTime updateTime;

    @Column(name="CHECK_TIME")
    private LocalDateTime checkTime;

    @Column(name="TABLE_COLLATION")
    private String tableCollation;

    @Column(name="CHECKSUM")
    private BigInteger checksum;

    @Column(name="CREATE_OPTIONS")
    private String createOptions;

    @Column(name="TABLE_COMMENT")
    private String tableComment;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public BigInteger getTableRows() {
        return tableRows;
    }

    public void setTableRows(BigInteger tableRows) {
        this.tableRows = tableRows;
    }

    public BigInteger getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(BigInteger avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    public BigInteger getDataLength() {
        return dataLength;
    }

    public void setDataLength(BigInteger dataLength) {
        this.dataLength = dataLength;
    }

    public BigInteger getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(BigInteger maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    public BigInteger getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(BigInteger indexLength) {
        this.indexLength = indexLength;
    }

    public BigInteger getDataFree() {
        return dataFree;
    }

    public void setDataFree(BigInteger dataFree) {
        this.dataFree = dataFree;
    }

    public BigInteger getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(BigInteger autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public BigInteger getChecksum() {
        return checksum;
    }

    public void setChecksum(BigInteger checksum) {
        this.checksum = checksum;
    }

    public String getCreateOptions() {
        return createOptions;
    }

    public void setCreateOptions(String createOptions) {
        this.createOptions = createOptions;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}