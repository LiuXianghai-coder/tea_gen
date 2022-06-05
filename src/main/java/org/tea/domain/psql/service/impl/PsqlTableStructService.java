package org.tea.domain.psql.service.impl;

import org.tea.domain.psql.entity.PsqlTableStructure;
import org.tea.entity.TabStructure;
import org.tea.service.TableStructureService;
import org.tea.tool.DataBaseTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @date 2022/6/4-下午9:25
 */
public class PsqlTableStructService implements TableStructureService {
    // 查找相关表的结构信息的 SQL 语句
    private final static String TABLES_INFO = "SELECT * FROM information_schema.columns WHERE table_catalog='?' AND table_name='?'";

    @Override
    public List<TabStructure> selectByTableName(String dbName, String tableName) {
        String sql = TABLES_INFO.replaceFirst("\\?", dbName).replace("?", tableName);
        List<PsqlTableStructure> structures = DataBaseTools.querySQL(sql, PsqlTableStructure.class);
        return new ArrayList<>(structures);
    }
}
