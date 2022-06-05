package org.tea.domain.psql.service.impl;

import org.tea.entity.SchemaStructure;
import org.tea.service.TableInfoService;
import org.tea.domain.psql.entity.PsqlSchemaStructure;
import org.tea.tool.DataBaseTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对 PostgresSQL 中有关 {@code TableInfoService} 的具体实现类
 *
 * @see TableInfoService
 */
public class PsqlTableInfoService implements TableInfoService {
    // 查找所有指定库中所所有的公共表
    private final static String SCHEMAS_SQL = "SELECT * FROM information_schema.tables " +
            "WHERE table_schema='public' AND table_catalog='?'";

    @Override
    public List<SchemaStructure> selectAllTables(String dbName) {
        List<PsqlSchemaStructure> schemas = DataBaseTools.querySQL(
                SCHEMAS_SQL.replace("?", dbName),
                PsqlSchemaStructure.class
        );

        return new ArrayList<>(schemas);
    }
}
