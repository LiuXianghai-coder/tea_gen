package org.tea.domain.psql.enums;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 有关实体类属性和 PostgresSQL 中的类型对应的枚举类
 *
 * @author lxh
 * @date 2022/6/4-下午5:40
 */
public enum PsqlTypeEnum {
    BOOL_PRIMITIVE(boolean.class, "BIT",  "boolean"),
    BOOL_BOXED(Boolean.class, "BIT", "boolean"),
    BIT_PRIMITIVE(boolean.class, "BIT", "bit"),
    BIT_BOXED(Boolean.class, "BIT", "bit"),
    BIG_INT_PRIMITIVE(long.class, "BIGINT","int8"),
    BIG_INT_BOXED(Long.class, "BIGINT", "int8"),
    BIG_SERIAL_PRIMITIVE(long.class, "BIGINT", "bigserial"),
    BIG_SERIAL_BOXED(Long.class, "BIGINT", "bigserial"),
    OID_PRIMITIVE(long.class, "BIGINT", "oid"),
    OID_BOXED(Long.class, "BIGINT", "oid"),
    BYTEA(byte[].class, "BINARY", "bytea"),
    CHAR(String.class, "CHAR", "char"),
    BP_CHAR(String.class, "CHAR", "bpchar"),
    NUMERIC(BigDecimal.class, "NUMERIC", "numeric"),
    INT_FOUR_PRIMITIVE(int.class, "INTEGER", "int4"),
    INT_FOUR_BOXED(Integer.class, "INTEGER", "int4"),
    SERIAL_PRIMITIVE(int.class, "INTEGER", "serial"),
    SERIAL_BOXED(Integer.class, "INTEGER", "serial"),
    INT_TWO_PRIMITIVE(short.class, "SMALLINT", "int2"),
    INT_TWO_BOXED(Short.class, "SMALLINT", "int2"),
    SMALL_SERIAL_PRIMITIVE(short.class, "SMALLINT", "smallserial"),
    SMALL_SERIAL_BOXED(Short.class, "SMALLINT", "smallserial"),
    FLOAT_FOUR_PRIMITIVE(float.class, "REAL", "float4"),
    FLOAT_FOUR_BOXED(Float.class, "REAL", "float4"),
    FLOAT_EIGHT_PRIMITIVE(double.class, "DOUBLE", "float8"),
    FLOAT_EIGHT_BOXED(Double.class, "DOUBLE", "float8"),
    MONEY_PRIMITIVE(double.class, "DOUBLE", "money"),
    MONEY_BOXED(Double.class, "DOUBLE", "money"),
    NAME(String.class, "VARCHAR", "name"),
    TEXT(String.class, "VARCHAR", "text"),
    VARCHAR(String.class, "VARCHAR", "varchar"),
    CHAR_VARYING(String.class, "CHARACTER VARYING", "VARCHAR"),
    DATE(Date.class, "DATE", "date"),
    TIME(Time.class, "TIME", "time"),
    TIME_ZONE(Time.class, "TIME", "timetz"),
    TIME_WITH_OUT_ZONE(Time.class, "TIME", "timestamp without time zone"),
    TIMESTAMP(Timestamp.class, "TIMESTAMP", "timestamp"),
    TIMESTAMP_ZONE(Timestamp.class, "TIMESTAMP", "timestamptz");

    public final Class<?> javaType;

    public final String jdbcType;

    public final String dbType;

    PsqlTypeEnum(Class<?> javaType, String jdbcType, String dbType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.dbType = dbType;
    }
}
