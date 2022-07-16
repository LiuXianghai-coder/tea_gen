package org.tea.entity;

import org.tea.service.FacadeService;

import java.util.List;

import static org.tea.tool.ConstTools.*;

/**
 * @author lxh
 * @date 2022/6/5-下午9:44
 */
public abstract class AbstractGenClass {

    private final FacadeService facadeService;

    protected AbstractGenClass(FacadeService facadeService) {
        this.facadeService = facadeService;
    }

    protected String genCommonEntityHeader(List<TabStructure> structures, SchemaStructure schema, String pack) {
        StringBuilder ans = new StringBuilder();
        String tableName;
        if (structures.size() == 0) return ans.toString();
        tableName = structures.get(0).getTableName();

        ans.append("package ").append(pack)
                .append("\n\n")
                .append("import javax.persistence.*;\n\n")
                .append(facadeService.genComment(schema.getSchemaComment()))
                .append("\n")
                .append("public class ")
                .append(toClassName(tableName))
                .append(" { \n");

        return ans.toString();
    }

    protected String genFieldColumn(TabStructure structure, Class<?> type) {
        return "\t" + facadeService.genComment(structure.getColComment()) + "\n" +
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

    protected String genMapperInterface(String pack, String tableName) {
        return "package " + pack +
                "\n\n" +
                "public interface " +
                toClassName(tableName) +
                "Mapper" +
                " {\n" + "}";
    }
}
