package org.tea.test;

import org.junit.jupiter.api.Test;
import org.tea.domain.mysql.enums.MySQLTypeEnum;
import org.tea.domain.psql.enums.PsqlTypeEnum;
import org.tea.tool.ConstTools;
import org.tea.tool.DataBaseTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxh
 * @date 2022/6/4-下午6:53
 */
public class EntityGenTest {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/lxh_db";
        String user = "lxh";
        String password = "12345678";
        String sql = "SELECT * FROM information_schema.columns WHERE table_name='columns'";
//        String sql = "SELECT * FROM information_schema.columns WHERE table_name='tables'";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        classLoader.loadClass("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<String[]> list = new ArrayList<>();
        while (rs.next()) {
            String colName = rs.getString("column_name");
            String type = rs.getString("data_type");
            list.add(new String[]{type, colName});
        }

        StringBuilder sb = new StringBuilder();
        sb.append("import org.tea.annotation.Column;\n\n")
                .append("public class TableStructure {")
                .append("\n");

        for (String[] val : list) {
            Class<?> type = null;
            for (PsqlTypeEnum psqlTypeEnum : PsqlTypeEnum.values()) {
                if (psqlTypeEnum.jdbcType.equalsIgnoreCase(val[0]) ||
                        psqlTypeEnum.dbType.equalsIgnoreCase(val[0])) {
                    type = psqlTypeEnum.javaType;
                    break;
                }
            }

            if (type == null) {
                System.out.println("未知的类型：" + val[0]);
                continue;
            }

            sb.append("\t")
                    .append("@Column(name=\"")
                    .append(val[1]).append("\")\n")
                    .append("\t")
                    .append("private ")
                    .append(type.getSimpleName())
                    .append(" ")
                    .append(ConstTools.toCamel(val[1]))
                    .append(";\n\n");
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

    @Test
    public void mysqlTest() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://39.99.129.90:3306/lxh";
        String user = "root";
        String password = "17358870357yi";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        classLoader.loadClass("org.postgresql.Driver");

//        String sql = "SHOW COLUMNS FROM information_schema.TABLES";
        String sql = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME='COLUMNS'";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<String[]> list = new ArrayList<>();

        while (rs.next()) {
            String colName = rs.getString("COLUMN_NAME");
            String type = rs.getString("DATA_TYPE");
            list.add(new String[]{type, colName});
        }

        StringBuilder sb = new StringBuilder();
        sb.append("public class MySQLTableStructure {\n");

        for (String[] val : list) {
            Class<?> type = null;
            for (MySQLTypeEnum typeEnum : MySQLTypeEnum.values()) {
                String tmp = val[0].toUpperCase();
                if (tmp.startsWith(typeEnum.jdbcType) ||
                        tmp.startsWith(typeEnum.dbType)) {
                    type = typeEnum.javaType;
                    break;
                }
            }

            if (type == null) {
                System.out.println("未知的类型：" + val[0]);
                continue;
            }

            sb.append("\t")
                    .append("@Column(name=\"")
                    .append(val[1]).append("\")\n")
                    .append("\t")
                    .append("private ")
                    .append(type.getSimpleName())
                    .append(" ")
                    .append(ConstTools.toCamel(val[1]))
                    .append(";\n\n");
        }

        sb.append("}");
        System.out.println(sb.toString());
    }
}
