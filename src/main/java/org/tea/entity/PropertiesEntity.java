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

    public PropertiesEntity(
            String url, String userName,
            String password, String dbName,
            String tableName, String filePath,
            String daType
    ) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.dbName = dbName;
        this.tableName = tableName;
        this.filePath = filePath;
        this.daType = daType;
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


    public static final class Builder {
        private String url;
        private String userName;
        private String password;
        private String dbName;
        private String tableName;
        private String filePath;
        private String daType;

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

        public PropertiesEntity build() {
            return new PropertiesEntity(url, userName, password, dbName, tableName, filePath, daType);
        }
    }
}
