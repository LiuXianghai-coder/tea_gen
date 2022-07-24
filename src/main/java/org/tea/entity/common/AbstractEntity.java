package org.tea.entity.common;

import org.tea.annotation.Column;

import java.sql.Date;

/**
 * @author lxh
 * @date 2022/7/24-下午1:55
 */
public abstract class AbstractEntity {
    @Column(name="del")
    private long del;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    @Column(name="update_user")
    private String updateUser;

    public long getDel() {
        return del;
    }

    public void setDel(long del) {
        this.del = del;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
