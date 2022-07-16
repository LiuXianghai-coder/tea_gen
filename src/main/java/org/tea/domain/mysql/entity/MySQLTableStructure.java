package org.tea.domain.mysql.entity;

import org.tea.annotation.Column;
import org.tea.entity.TabStructure;

/**
 * @author lxh
 * @date 2022/6/5-下午9:03
 */
public class MySQLTableStructure extends TabStructure {
    @Column(name="CHARACTER_MAXIMUM_LENGTH")
    private Long characterMaximumLength;

    @Column(name="CHARACTER_OCTET_LENGTH")
    private Long characterOctetLength;

    @Column(name="CHARACTER_SET_NAME")
    private String characterSetName;

    @Column(name="COLLATION_NAME")
    private String collationName;

    @Column(name="COLUMN_COMMENT")
    private String columnComment;

    @Column(name="COLUMN_DEFAULT")
    private String columnDefault;

    @Column(name="COLUMN_KEY")
    private String columnKey;

    @Column(name="COLUMN_TYPE")
    private String columnType;

    @Column(name="DATETIME_PRECISION")
    private long datetimePrecision;

    @Column(name="EXTRA")
    private String extra;

    @Column(name="GENERATION_EXPRESSION")
    private String generationExpression;

    @Column(name="IS_NULLABLE")
    private String isNullable;

    @Column(name="NUMERIC_PRECISION")
    private Long numericPrecision;

    @Column(name="NUMERIC_SCALE")
    private Long numericScale;

    @Column(name="ORDINAL_POSITION")
    private long ordinalPosition;

    @Column(name="PRIVILEGES")
    private String privileges;

    @Column(name="SRS_ID")
    private long srsId;

    @Column(name="TABLE_CATALOG")
    private String tableCatalog;

    @Column(name="TABLE_SCHEMA")
    private String tableSchema;

    public Long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public Long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Long numericScale) {
        this.numericScale = numericScale;
    }

    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public long getSrsId() {
        return srsId;
    }

    public void setSrsId(long srsId) {
        this.srsId = srsId;
    }

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

    @Override
    public String getColComment() {
        return columnComment;
    }
}
