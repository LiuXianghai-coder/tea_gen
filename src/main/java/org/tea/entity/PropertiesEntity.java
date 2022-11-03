package org.tea.entity;

/**
 * @author lxh
 * @date 2022/6/5-上午8:36
 */
public class PropertiesEntity {
    private final String url;
    private final String userName;
    private final String password;
    private final String dbName;
    private final String tableName;
    private final String filePath;

    private final String daType;

    private final String mapperDir;

    private final String entityDir;

    private final String xmlDir;

    private final String baseDir;

    private final String sc; // super class

    private final String sm; // super mapper

    private PropertiesEntity(
            String url, String userName,
            String password, String dbName,
            String tableName, String filePath,
            String daType, String mapperDir,
            String entityDir, String xmlDir,
            String baseDir, String sc, String sm) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
        this.filePath = filePath;
        this.daType = daType;
        this.mapperDir = mapperDir;
        this.entityDir = entityDir;
        this.xmlDir = xmlDir;
        this.baseDir = baseDir;
        this.sc = sc;
        this.sm = sm;
    }

    public String getDaType() {
        return daType;
    }

    public String getMapperDir() {
        return mapperDir;
    }

    public String getEntityDir() {
        return entityDir;
    }

    public String getXmlDir() {
        return xmlDir;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public String getSc() {
        return sc;
    }

    public String getSm() {
        return sm;
    }

    public static final class Builder {
        private String url;
        private String userName;
        private String password;
        private String dbName;
        private String tableName;
        private String filePath;
        private String daType;
        private String mapperDir;
        private String entityDir;
        private String xmlDir;
        private String baseDir;
        private String sc;
        private String sm;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder withDaType(String daType) {
            this.daType = daType;
            return this;
        }

        public Builder withMapperDir(String mapperDir) {
            this.mapperDir = mapperDir;
            return this;
        }

        public Builder withEntityDir(String entityDir) {
            this.entityDir = entityDir;
            return this;
        }

        public Builder withXmlDir(String xmlDir) {
            this.xmlDir = xmlDir;
            return this;
        }

        public Builder withBaseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public Builder withSc(String sc) {
            this.sc = sc;
            return this;
        }

        public Builder withSm(String sm) {
            this.sm = sm;
            return this;
        }

        public PropertiesEntity build() {
            return new PropertiesEntity(url, userName, password, dbName, tableName,
                    filePath, daType, mapperDir, entityDir, xmlDir, baseDir, sc, sm);
        }
    }
}
