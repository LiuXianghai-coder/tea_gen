package org.tea.domain.mysql;

import com.google.common.collect.Sets;
import org.tea.annotation.Column;
import org.tea.domain.mysql.enums.MySQLTypeEnum;
import org.tea.entity.AbstractGenClass;
import org.tea.entity.TabStructure;
import org.tea.service.SuperClassService;
import org.tea.service.impl.FacadeService;
import org.tea.tool.ClassTools;
import org.tea.tool.ConstTools;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * 和 MySQL 特定处理相关的抽象父类，定义一些常用的接口
 *
 * @author lxh
 * @date 2022/7/24-下午1:43
 */
public abstract class AbstractMySQLService extends AbstractGenClass
        implements SuperClassService {

    protected AbstractMySQLService(FacadeService facadeService) {
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

    @Override
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
    public boolean willGen(TabStructure struct, Class<?> sc) {
        List<Field> fields = ClassTools.listAllFields(sc);
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            String tarName = ConstTools.toCamel(struct.getColumnName());
            Class<?> tarType = findJavaType(struct);

            // 父类已经存在相关的属性，跳过后续的处理
            if (fieldName.equals(tarName) && tarType == fieldType) return false;
        }

        return true;
    }
}
