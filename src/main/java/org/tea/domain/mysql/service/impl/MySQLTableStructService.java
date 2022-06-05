package org.tea.domain.mysql.service.impl;

import org.tea.domain.mysql.entity.MySQLTableStructure;
import org.tea.entity.TabStructure;
import org.tea.service.TableStructureService;
import org.tea.tool.DataBaseTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @date 2022/6/5-下午9:21
 */
public class MySQLTableStructService implements TableStructureService {
    private static final String SQL = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='?' AND TABLE_NAME='?'";
    @Override
    public List<TabStructure> selectByTableName(String dbName, String tableName) {
        String sql = SQL.replaceFirst("\\?", dbName).replace("?", tableName);
        List<MySQLTableStructure> structures = DataBaseTools.querySQL(sql, MySQLTableStructure.class);
        return new ArrayList<>(structures);
    }
}
