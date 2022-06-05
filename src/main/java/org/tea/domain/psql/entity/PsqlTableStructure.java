package org.tea.domain.psql.entity;

import org.tea.annotation.Column;
import org.tea.entity.TabStructure;

/**
 * 和 PostgresSQL 中表结构对应的相关字段,
 * 重点关注一下 taleName、columnName 以及 data_type 即可
 *
 * @author lxh
 * @date 2022/6/4-下午7:12
 */
public class PsqlTableStructure extends TabStructure {
    @Column(name="numeric_precision")
    private int numericPrecision;

    @Column(name="numeric_precision_radix")
    private int numericPrecisionRadix;

    @Column(name="numeric_scale")
    private int numericScale;

    @Column(name="datetime_precision")
    private int datetimePrecision;

    @Column(name="ordinal_position")
    private int ordinalPosition;

    @Column(name="maximum_cardinality")
    private int maximumCardinality;

    @Column(name="interval_precision")
    private int intervalPrecision;

    @Column(name="character_maximum_length")
    private int characterMaximumLength;

    @Column(name="character_octet_length")
    private int characterOctetLength;

    @Column(name="character_set_schema")
    private String characterSetSchema;

    @Column(name="character_set_name")
    private String characterSetName;

    @Column(name="collation_catalog")
    private String collationCatalog;

    @Column(name="collation_schema")
    private String collationSchema;

    @Column(name="collation_name")
    private String collationName;

    @Column(name="domain_catalog")
    private String domainCatalog;

    @Column(name="domain_schema")
    private String domainSchema;

    @Column(name="domain_name")
    private String domainName;

    @Column(name="udt_catalog")
    private String udtCatalog;

    @Column(name="udt_schema")
    private String udtSchema;

    @Column(name="udt_name")
    private String udtName;

    @Column(name="scope_catalog")
    private String scopeCatalog;

    @Column(name="scope_schema")
    private String scopeSchema;

    @Column(name="scope_name")
    private String scopeName;

    @Column(name="dtd_identifier")
    private String dtdIdentifier;

    @Column(name="is_self_referencing")
    private String isSelfReferencing;

    @Column(name="is_identity")
    private String isIdentity;

    @Column(name="identity_generation")
    private String identityGeneration;

    @Column(name="identity_start")
    private String identityStart;

    @Column(name="identity_increment")
    private String identityIncrement;

    @Column(name="identity_maximum")
    private String identityMaximum;

    @Column(name="identity_minimum")
    private String identityMinimum;

    @Column(name="identity_cycle")
    private String identityCycle;

    @Column(name="is_generated")
    private String isGenerated;

    @Column(name="generation_expression")
    private String generationExpression;

    @Column(name="table_catalog")
    private String tableCatalog;

    @Column(name="is_updatable")
    private String isUpdatable;

    @Column(name="table_schema")
    private String tableSchema;

    @Column(name="table_name")
    private String tableName;

    @Column(name="column_default")
    private String columnDefault;

    @Column(name="is_nullable")
    private String isNullable;

    @Column(name="interval_type")
    private String intervalType;

    @Column(name="character_set_catalog")
    private String characterSetCatalog;

    public int getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(int numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public int getNumericPrecisionRadix() {
        return numericPrecisionRadix;
    }

    public void setNumericPrecisionRadix(int numericPrecisionRadix) {
        this.numericPrecisionRadix = numericPrecisionRadix;
    }

    public int getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(int numericScale) {
        this.numericScale = numericScale;
    }

    public int getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(int datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public int getMaximumCardinality() {
        return maximumCardinality;
    }

    public void setMaximumCardinality(int maximumCardinality) {
        this.maximumCardinality = maximumCardinality;
    }

    public int getIntervalPrecision() {
        return intervalPrecision;
    }

    public void setIntervalPrecision(int intervalPrecision) {
        this.intervalPrecision = intervalPrecision;
    }

    public int getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(int characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public int getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(int characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public String getCharacterSetSchema() {
        return characterSetSchema;
    }

    public void setCharacterSetSchema(String characterSetSchema) {
        this.characterSetSchema = characterSetSchema;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationCatalog() {
        return collationCatalog;
    }

    public void setCollationCatalog(String collationCatalog) {
        this.collationCatalog = collationCatalog;
    }

    public String getCollationSchema() {
        return collationSchema;
    }

    public void setCollationSchema(String collationSchema) {
        this.collationSchema = collationSchema;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getDomainCatalog() {
        return domainCatalog;
    }

    public void setDomainCatalog(String domainCatalog) {
        this.domainCatalog = domainCatalog;
    }

    public String getDomainSchema() {
        return domainSchema;
    }

    public void setDomainSchema(String domainSchema) {
        this.domainSchema = domainSchema;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUdtCatalog() {
        return udtCatalog;
    }

    public void setUdtCatalog(String udtCatalog) {
        this.udtCatalog = udtCatalog;
    }

    public String getUdtSchema() {
        return udtSchema;
    }

    public void setUdtSchema(String udtSchema) {
        this.udtSchema = udtSchema;
    }

    public String getUdtName() {
        return udtName;
    }

    public void setUdtName(String udtName) {
        this.udtName = udtName;
    }

    public String getScopeCatalog() {
        return scopeCatalog;
    }

    public void setScopeCatalog(String scopeCatalog) {
        this.scopeCatalog = scopeCatalog;
    }

    public String getScopeSchema() {
        return scopeSchema;
    }

    public void setScopeSchema(String scopeSchema) {
        this.scopeSchema = scopeSchema;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getDtdIdentifier() {
        return dtdIdentifier;
    }

    public void setDtdIdentifier(String dtdIdentifier) {
        this.dtdIdentifier = dtdIdentifier;
    }

    public String getIsSelfReferencing() {
        return isSelfReferencing;
    }

    public void setIsSelfReferencing(String isSelfReferencing) {
        this.isSelfReferencing = isSelfReferencing;
    }

    public String getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(String isIdentity) {
        this.isIdentity = isIdentity;
    }

    public String getIdentityGeneration() {
        return identityGeneration;
    }

    public void setIdentityGeneration(String identityGeneration) {
        this.identityGeneration = identityGeneration;
    }

    public String getIdentityStart() {
        return identityStart;
    }

    public void setIdentityStart(String identityStart) {
        this.identityStart = identityStart;
    }

    public String getIdentityIncrement() {
        return identityIncrement;
    }

    public void setIdentityIncrement(String identityIncrement) {
        this.identityIncrement = identityIncrement;
    }

    public String getIdentityMaximum() {
        return identityMaximum;
    }

    public void setIdentityMaximum(String identityMaximum) {
        this.identityMaximum = identityMaximum;
    }

    public String getIdentityMinimum() {
        return identityMinimum;
    }

    public void setIdentityMinimum(String identityMinimum) {
        this.identityMinimum = identityMinimum;
    }

    public String getIdentityCycle() {
        return identityCycle;
    }

    public void setIdentityCycle(String identityCycle) {
        this.identityCycle = identityCycle;
    }

    public String getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(String isGenerated) {
        this.isGenerated = isGenerated;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getIsUpdatable() {
        return isUpdatable;
    }

    public void setIsUpdatable(String isUpdatable) {
        this.isUpdatable = isUpdatable;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getCharacterSetCatalog() {
        return characterSetCatalog;
    }

    public void setCharacterSetCatalog(String characterSetCatalog) {
        this.characterSetCatalog = characterSetCatalog;
    }
}