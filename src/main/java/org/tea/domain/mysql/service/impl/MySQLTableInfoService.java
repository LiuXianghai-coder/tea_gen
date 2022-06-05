package org.tea.domain.mysql.service.impl;

import org.tea.domain.mysql.entity.MySQLSchemaStructure;
import org.tea.entity.SchemaStructure;
import org.tea.service.TableInfoService;
import org.tea.tool.DataBaseTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @date 2022/6/5-下午9:21
 */
public class MySQLTableInfoService implements TableInfoService {
    private static final String SQL = "SELECT * FROM information_schema.TABLES " +
            "WHERE TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA='?'";

    @Override
    public List<SchemaStructure> selectAllTables(String dbName) {
        List<MySQLSchemaStructure> structures = DataBaseTools.querySQL(
                SQL.replace("?", dbName),
                MySQLSchemaStructure.class
        );

        return new ArrayList<>(structures);
    }
}
