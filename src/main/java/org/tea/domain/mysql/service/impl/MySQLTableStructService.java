package org.tea.domain.mysql.service.impl;

import org.tea.domain.mysql.AbstractMySQLService;
import org.tea.domain.mysql.entity.MySQLTableStructure;
import org.tea.domain.mysql.enums.MySQLTypeEnum;
import org.tea.entity.TabStructure;
import org.tea.service.SuperClassService;
import org.tea.service.TableStructureService;
import org.tea.service.impl.FacadeService;
import org.tea.tool.DataBaseTools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lxh
 * @date 2022/6/5-下午9:21
 */
public class MySQLTableStructService extends AbstractMySQLService
        implements TableStructureService, SuperClassService {
    private static final String SQL = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='?' AND TABLE_NAME='?'";

    public MySQLTableStructService(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    public List<TabStructure> selectByTableName(String dbName, String tableName) {
        String sql = SQL.replaceFirst("\\?", dbName).replace("?", tableName);
        List<MySQLTableStructure> structures = DataBaseTools.querySQL(sql, MySQLTableStructure.class);
        return new ArrayList<>(structures);
    }

    @Override
    public List<TabStructure> filterColumns(List<TabStructure> structs, Class<?> clazz) {
        return structs.stream().filter(obj -> willGen(obj, clazz))
                .collect(Collectors.toList());
    }
}
