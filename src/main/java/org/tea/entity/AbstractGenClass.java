package org.tea.entity;

import org.tea.constant.CodeTemplate;
import org.tea.service.SuperClassService;
import org.tea.service.impl.FacadeService;
import org.tea.tool.ClassTools;
import org.tea.tool.ConstTools;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.tea.tool.ConstTools.*;

/**
 * @author lxh
 * @date 2022/6/5-下午9:44
 */
public abstract class AbstractGenClass implements SuperClassService {

    private final FacadeService facadeService;

    protected AbstractGenClass(FacadeService facadeService) {
        this.facadeService = facadeService;
    }

    protected String genCommonEntityHeader(List<TabStructure> structures, String pack, Class<?> sc) {
        StringBuilder ans = new StringBuilder();
        String tableName;
        if (structures.size() == 0) return ans.toString();
        tableName = structures.get(0).getTableName();

        ans.append("package ").append(pack)
                .append("\n\n")
                .append(sc == null ? "" : "import " + sc.getName() + ";\n")
                .append(genImports(structures)) // 相关类型需要的导入语句
                .append("import javax.persistence.*;\n")
                .append("\n")
                .append("@Table(name=\"").append(tableName).append("\")\n")
                .append("public class ")
                .append(toClassName(tableName))
                .append(sc == null ? "" : " extends " + sc.getSimpleName())
                .append(" { \n");

        return ans.toString();
    }

    protected String genFieldColumn(TabStructure structure, Class<?> type) {
        String idAnno = structure.isPrimaryKey() ? "\t@Id\n" : "";
        return "\t" + facadeService.genComment(structure.getColComment()) +
                "\n" + idAnno +
                "\t" + "@Column(name=\"" +
                structure.getColumnName() + "\")\n" +
                "\t" +
                "private " +
                type.getSimpleName() +
                " " + toCamel(structure.getColumnName()) +
                ";\n\n";
    }

    protected String genXmlColMap(TabStructure structure, Class<?> type, String jdbcType) {
        return "\t\t<result " + "column=\"" +
                structure.getColumnName() + "\" " +
                "jdbcType=\"" + jdbcType + "\" " +
                "javaType=\"" + type.getName() + "\" " +
                " property=\"" +
                toCamel(structure.getColumnName()) +
                "\" />\n";
    }

    protected String genMapperInterface(String pack, String tableName, Class<?> sc) {
        return "package " + pack + "\n\n" +
                (sc == null ? "" : "import " + sc.getName() + ";\n") +
                "public interface " + toClassName(tableName) + "Mapper" +
                (sc == null ? "" : sc.getSimpleName()) + " {\n" + "}";
    }

    /**
     * 生成相关的需要导入的类的导入语句
     *
     * @param structs 表结构列表
     * @return 相关的导入语句
     */
    protected String genImports(List<TabStructure> structs) {
        Set<Class<?>> set = satisFieldTypes(structs);
        StringBuilder sb = new StringBuilder();
        for (Class<?> clazz : set) {
            if (ClassTools.isBasicClass(clazz)) continue;
            sb.append("import ").append(clazz.getName()).append(";\n");
        }
        sb.append("\nimport java.util.Objects;\n");

        return sb.toString();
    }

    /**
     * 生成指定列对应的 Getter 方法
     */
    private String genGetter(TabStructure struct) {
        StringBuilder ans = new StringBuilder("\tpublic ");
        Class<?> type = findJavaType(struct);
        ans.append(type.getSimpleName());
        String mn = "get" + toClassName(struct.getColumnName());
        ans.append(" ").append(mn).append("()").append("{\n");
        ans.append("\t\treturn ").append("this.")
                .append(ConstTools.toCamel(struct.getColumnName()))
                .append(";\n\t}\n");
        return ans.toString();
    }

    /**
     * 生成对应列的 Setter 方法
     */
    private String genSetter(TabStructure struct) {
        StringBuilder ans = new StringBuilder("\tpublic void ");
        Class<?> type = findJavaType(struct);
        String mn = "set" + toClassName(struct.getColumnName());
        String vn = toCamel(struct.getColumnName());
        ans.append(mn).append("(").append(type.getSimpleName())
                .append(" ").append(vn).append(") {\n")
                .append("\t\t").append("this.").append(vn).append(" = ")
                .append(vn).append(";\n\t}\n");
        return ans.toString();
    }

    protected String genSetAndGet(List<TabStructure> structs) {
        StringBuilder sg = new StringBuilder();
        for (TabStructure struct : structs) {
            sg.append(genGetter(struct)).append("\n")
                    .append(genSetter(struct)).append("\n");
        }
        return sg.toString();
    }

    /**
     * 生成相关实体类对象的 equals 方法
     */
    private String genEquals(List<TabStructure> struts) {
        if (struts.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(CodeTemplate.EQUALS_METHOD_TMP);
        String clazzName = ConstTools.toClassName(struts.get(0).getTableName());
        sb.append("\n\t\t").append(clazzName).append(" obj")
                .append(" = ").append("(").append(clazzName).append(") ")
                .append("o;\n\t\treturn ");
        int sz = struts.size();
        for (int i = 0; i < sz; ++i) {
            TabStructure struct = struts.get(i);
            Class<?> type = findJavaType(struct);
            String fieldName = ConstTools.toCamel(struct.getColumnName());
            if (type.isPrimitive()) {
                sb.append(" ").append("this.").append(fieldName)
                        .append(" == ").append("obj.").append(fieldName);
            } else {
                sb.append(" ").append("Objects.equals(").append("this.").append(fieldName)
                        .append(", ").append("obj.").append(fieldName).append(")");
            }

            if (i != sz - 1) sb.append(" &&\n\t\t\t\t");
        }
        sb.append(";\n\t}\n");
        return sb.toString();
    }

    /**
     * 生成相关实体类的 hashcode 方法
     */
    private String genHashCode(List<TabStructure> structs) {
        StringBuilder sb = new StringBuilder(CodeTemplate.HASH_CODE_TMP);
        sb.append("\t\treturn Objects.hashCode(");
        int sz = structs.size();
        for (int i = 0, j = 1; i < sz; ++i, ++j) {
            TabStructure struct = structs.get(i);
            String fieldName = ConstTools.toCamel(struct.getColumnName());
            sb.append(fieldName);
            if (i != sz - 1) sb.append(", ");
            if (j % 8 == 0) sb.append("\n\t\t\t\t");
        }
        sb.append(");\n\t}\n");
        return sb.toString();
    }

    protected String genEqualsAndHashCode(List<TabStructure> structs) {
        return genEquals(structs) + "\n" +
                genHashCode(structs) + "\n";
    }

    /**
     * 统计当前生成的类中，存在的不同的类型，使得能够生成对应的导入语句
     *
     * @param struts 当前正在处理的表结构的列信息
     * @return 当前生成的目标类需要的类型
     */
    protected abstract Set<Class<?>> satisFieldTypes(List<TabStructure> struts);

    /**
     * 根据相关的列对象，得到当前列对应的 Java 类型
     *
     * @param struct 列对象
     * @return 对应的 Java 类型
     */
    protected abstract Class<?> findJavaType(TabStructure struct);

    @Override
    public boolean willGen(TabStructure struct, Class<?> sc) {
        List<Field> fields = ClassTools.listAllFields(sc);

        String tarName = ConstTools.toCamel(struct.getColumnName());
        Class<?> tarType = findJavaType(struct);
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            // 父类已经存在相关的属性，跳过后续的处理
            if (fieldName.equals(tarName) && tarType == fieldType) return false;
        }

        return true;
    }
}
