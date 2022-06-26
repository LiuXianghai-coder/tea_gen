package org.tea.tool;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tea.annotation.Column;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTools {
    private final static Logger log = LoggerFactory.getLogger(DataBaseTools.class);

    private static Connection conn = null;

    public static boolean checkConnect(
            String url, String userName,
            String password, String type
    )  {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> driverClass = null;
            if (type.equalsIgnoreCase("MySQL")) {
                driverClass = classLoader.loadClass("com.mysql.cj.jdbc.Driver");
            } else if (type.equalsIgnoreCase("PostgresSQL")) {
                driverClass = classLoader.loadClass("org.postgresql.Driver");
            }

            if (driverClass == null) {
                log.info("无法加载对应的数据库连接驱动");
                return false;
            }

            conn = DriverManager.getConnection(url, userName, password);
            if (conn != null) return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }

        return false;
    }

    public static <T> List<T> querySQL(String sql, Class<T> clazz) {
        List<T> ans = new ArrayList<>();
        if (conn == null) {
            log.info("未能创建对应的数据库连接， 无法执行查询");
            return ans;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<Field> fields = getAllField(new ArrayList<>(), clazz);

            while (rs.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);
                    if (column == null) {
                        throw new RuntimeException("实体类属性缺乏必需的 @Column 注解");
                    }

                    String colName = column.name();
                    Object val = rs.getObject(colName);
                    if (val == null) continue;

                    field.setAccessible(true);
                    field.set(instance, val);
                }
                ans.add(instance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ans;
    }

    public static List<Field> getAllField(List<Field> fields, Class<?> type) {
        fields.addAll(Lists.newArrayList(type.getDeclaredFields()));

        if (type.getSuperclass() != null)
            getAllField(fields, type.getSuperclass());
        return fields;
    }
}
