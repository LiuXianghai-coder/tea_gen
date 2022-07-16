package org.tea.domain.psql.entity;

import org.tea.annotation.Column;
import org.tea.entity.SchemaStructure;

/**
 * @author lxh
 * @date 2022/6/4-下午9:09
 */
public class PsqlSchemaStructure extends SchemaStructure {
    @Column(name="table_catalog")
    private String tableCatalog;

    @Column(name="table_schema")
    private String tableSchema;

    @Column(name="table_type")
    private String tableType;

    @Column(name="self_referencing_column_name")
    private String selfReferencingColumnName;

    @Column(name="reference_generation")
    private String referenceGeneration;

    @Column(name="user_defined_type_catalog")
    private String userDefinedTypeCatalog;

    @Column(name="user_defined_type_schema")
    private String userDefinedTypeSchema;

    @Column(name="user_defined_type_name")
    private String userDefinedTypeName;

    @Column(name="is_insertable_into")
    private String isInsertableInto;

    @Column(name="is_typed")
    private String isTyped;

    @Column(name="commit_action")
    private String commitAction;

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

    public String getSelfReferencingColumnName() {
        return selfReferencingColumnName;
    }

    public void setSelfReferencingColumnName(String selfReferencingColumnName) {
        this.selfReferencingColumnName = selfReferencingColumnName;
    }

    public String getReferenceGeneration() {
        return referenceGeneration;
    }

    public void setReferenceGeneration(String referenceGeneration) {
        this.referenceGeneration = referenceGeneration;
    }

    public String getUserDefinedTypeCatalog() {
        return userDefinedTypeCatalog;
    }

    public void setUserDefinedTypeCatalog(String userDefinedTypeCatalog) {
        this.userDefinedTypeCatalog = userDefinedTypeCatalog;
    }

    public String getUserDefinedTypeSchema() {
        return userDefinedTypeSchema;
    }

    public void setUserDefinedTypeSchema(String userDefinedTypeSchema) {
        this.userDefinedTypeSchema = userDefinedTypeSchema;
    }

    public String getUserDefinedTypeName() {
        return userDefinedTypeName;
    }

    public void setUserDefinedTypeName(String userDefinedTypeName) {
        this.userDefinedTypeName = userDefinedTypeName;
    }

    public String getIsInsertableInto() {
        return isInsertableInto;
    }

    public void setIsInsertableInto(String isInsertableInto) {
        this.isInsertableInto = isInsertableInto;
    }

    public String getIsTyped() {
        return isTyped;
    }

    public void setIsTyped(String isTyped) {
        this.isTyped = isTyped;
    }

    public String getCommitAction() {
        return commitAction;
    }

    public void setCommitAction(String commitAction) {
        this.commitAction = commitAction;
    }

    @Override
    public String getSchemaComment() {
        return null;
    }
}