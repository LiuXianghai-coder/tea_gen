package org.tea.entity;

import org.tea.tool.DataBaseTools;

import java.util.List;

/**
 * @author lxh
 * @date 2022/6/5-下午9:44
 */
public abstract class AbstractGenClass {
    protected String genCommonEntityHeader(List<TabStructure> structures, String pack) {
        StringBuilder ans = new StringBuilder();
        String tableName = null;
        if (structures.size() == 0) return ans.toString();
        tableName = structures.get(0).getTableName();

        ans.append("package ").append(pack)
                .append("\n\n")
                .append("import javax.persistence.*;\n\n")
                .append("public class ")
                .append(DataBaseTools.toClassName(tableName))
                .append(" { \n");

        return ans.toString();
    }

    protected String genFieldColumn(TabStructure structure, Class<?> type) {
        return "\t" + "@Column(name=\"" +
                structure.getColumnName() + "\")\n" +
                "\t" +
                "private " +
                type.getSimpleName() +
                " " +
                DataBaseTools.toCamel(structure.getColumnName()) +
                ";\n\n";
    }

    protected String genXmlColMap(TabStructure structure, Class<?> type, String jdbcType) {
        return "\t\t<result " + "column=\"" +
                structure.getColumnName() + "\" " +
                "jdbcType=\"" + jdbcType + "\"" +
                " property=\"" +
                DataBaseTools.toCamel(structure.getColumnName()) +
                "\" />\n";
    }

    protected String genMapperInterface(String pack, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(pack)
                .append("\n\n")
                .append("public interface ")
                .append(DataBaseTools.toClassName(tableName))
                .append("Mapper")
                .append(" {\n").append("}");

        return sb.toString();
    }
}
