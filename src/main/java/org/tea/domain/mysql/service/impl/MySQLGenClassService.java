package org.tea.domain.mysql.service.impl;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tea.domain.mysql.enums.MySQLTypeEnum;
import org.tea.entity.AbstractGenClass;
import org.tea.entity.SchemaStructure;
import org.tea.entity.TabStructure;
import org.tea.service.FacadeService;
import org.tea.service.GenClassService;

import java.util.List;
import java.util.Set;

import static org.tea.constant.CodeTemplate.RESULT_XML_TEMP;
import static org.tea.constant.CodeTemplate.XML_TEMPLATE;

/**
 * @author lxh
 * @date 2022/6/5-下午9:21
 */
public class MySQLGenClassService
        extends AbstractGenClass
        implements GenClassService {
    private final static Logger log = LoggerFactory.getLogger(MySQLGenClassService.class);

    public MySQLGenClassService(FacadeService facadeService) {
        super(facadeService);
    }

    @Override
    protected Set<Class<?>> satisFieldTypes(List<TabStructure> struts) {
        Set<Class<?>> res = Sets.newHashSet();
        for (TabStructure structure : struts) {
            Class<?> type = findJavaType(structure);
            if (type != null) res.add(type);
        }
        return res;
    }

    protected Class<?> findJavaType(TabStructure structure) {
        Class<?> type = null;
        String dataType = structure.getDataType().toUpperCase();
        for (MySQLTypeEnum typeEnum : MySQLTypeEnum.values()) {
            if (dataType.startsWith(typeEnum.jdbcType) ||
                    dataType.startsWith(typeEnum.dbType)) {
                type = typeEnum.javaType;
                break;
            }
        }
        return type;
    }

    @Override
    public String genEntityByStruct(List<TabStructure> structures, SchemaStructure schema, String pack) {
        StringBuilder ans = new StringBuilder(genCommonEntityHeader(structures, schema, pack));
        for (TabStructure structure : structures) {
            Class<?> type = findJavaType(structure);

            if (type == null) {
                log.info("未知的类型：{}" + structure.getDataType());
                continue;
            }

            ans.append(genFieldColumn(structure, type));
        }
        ans.append(genSetAndGet(structures));
        ans.append(genEqualsAndHashCode(structures, schema));
        ans.append("}");

        return ans.toString();
    }

    @Override
    public String genXmlMapperByStruct(List<TabStructure> structures, String entity, String mapper) {
        String firstXml = XML_TEMPLATE.replace("#", mapper);
        String res = RESULT_XML_TEMP.replace("#", entity);
        StringBuilder sb = new StringBuilder();
        sb.append(firstXml).append(res);

        for (TabStructure structure : structures) {
            MySQLTypeEnum type = null;

            String dataType = structure.getDataType();
            for (MySQLTypeEnum typeEnum : MySQLTypeEnum.values()) {
                if (dataType.startsWith(typeEnum.jdbcType) ||
                        dataType.startsWith(typeEnum.dbType)) {
                    type = typeEnum;
                    break;
                }
            }

            if (type == null) {
                log.info("未知的类型 {}", dataType);
                continue;
            }

            sb.append(genXmlColMap(structure, type.javaType, type.jdbcType));
        }
        sb.append("\t</resultMap>\n").append("</mapper>");

        return sb.toString();
    }

    @Override
    public String genMapperByStruct(List<TabStructure> structures, String pack) {
        StringBuilder sb = new StringBuilder();
        if (structures.size() == 0) return sb.toString();
        String tableName = structures.get(0).getTableName();
        sb.append(genMapperInterface(pack, tableName));

        return sb.toString();
    }

    @Override
    public String genJpaRepoByStruct(List<TabStructure> structures, String entity, String pack) {
        return null;
    }
}
