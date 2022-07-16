package org.tea.domain.mysql.enums;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * MySQL 中的数据类型和 Java、JDBC 中对应的类型
 *
 * @author lxh
 * @date 2022/6/5-下午8:23
 */
public enum MySQLTypeEnum {
    BIT_PRIMITIVE(boolean.class, "BIT", "BIT"),
    BIT_BOXED(Boolean.class, "BIT", "BIT"),
    TINYINT_PRIMITIVE(int.class, "TINYINT", "TINYINT"),
    TINYINT_BOXED(Integer.class, "TINYINT", "TINYINT"),
    BOOL_PRIMITIVE(boolean.class, "TINYINT", "BOOL"),
    BOOL_BOXED(Boolean.class, "TINYINT", "BOOL"),
    BOOLEAN_PRIMITIVE(boolean.class, "TINYINT", "BOOLEAN"),
    BOOLEAN_BOXED(Boolean.class, "TINYINT", "BOOLEAN"),
    SMALLINT_PRIMITIVE(int.class, "SMALLINT", "SMALLINT"),
    SMALLINT_BOXED(Integer.class, "SMALLINT", "SMALLINT"),
    MEDIUMINT_PRIMITIVE(int.class, "MEDIUMINT", "MEDIUMINT"),
    MEDIUMINT_BOXED(Integer.class, "MEDIUMINT", "MEDIUMINT"),
    INT_PRIMITIVE(long.class, "INTEGER", "INT"),
    INT_BOXED(Long.class, "INTEGER", "INT"),
    INTEGER_PRIMITIVE(long.class, "INTEGER", "INTEGER"),
    INTEGER_BOXED(Long.class, "INTEGER", "INTEGER"),
    BIGINT(BigInteger.class, "BIGINT", "BIGINT"),
    FLOAT_PRIMITIVE(float.class, "FLOAT", "FLOAT"),
    FLOAT_BOXED(Float.class, "FLOAT", "FLOAT"),
    DOUBLE_PRIMITIVE(double.class, "DOUBLE", "DOUBLE"),
    DOUBLE_BOXED(Double.class, "DOUBLE", "DOUBLE"),
    DECIMAL(BigDecimal.class, "DECIMAL", "DECIMAL"),
    DATE(Date.class, "DATE", "DATE"),
    DATETIME(LocalDateTime.class, "DATETIME", "DATETIME"),
    TIMESTAMP(LocalDateTime.class, "TIMESTAMP", "TIMESTAMP"),
    YEAR(Date.class, "YEAR", "YEAR"),
    CHAR(String.class, "CHAR", "CHAR"),
    VARCHAR(String.class, "VARCHAR", "VARCHAR"),
    BINARY(byte[].class, "BINARY", "BINARY"),
    VARBINARY(byte[].class, "VARBINARY", "VARBINARY"),
    TINYBLOB(byte[].class, "TINYBLOB", "TINYBLOB"),
    TINYTEXT(String.class, "TINYTEXT", "TINYTEXT"),
    BLOB(byte[].class, "BLOB", "BLOB"),
    TEXT(String.class, "VARCHAR", "TEXT"),
    LONG_TEXT(String.class, "VARCHAR", "LONGTEXT"),
    MEDIUMBLOB(byte[].class, "MEDIUMBLOB", "MEDIUMBLOB"),
    MEDIUMTEXT(String.class, "VARCHAR", "MEDIUMTEXT"),
    LONGBLOB(byte[].class, "LONGBLOB", "LONGBLOB"),
    ENUM(String.class, "CHAR", "ENUM"),
    SET(String.class, "CHAR", "SET");

    public final Class<?> javaType;
    public final String jdbcType;
    public final String dbType;

    MySQLTypeEnum(Class<?> javaType, String jdbcType, String dbType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.dbType = dbType;
    }
}
