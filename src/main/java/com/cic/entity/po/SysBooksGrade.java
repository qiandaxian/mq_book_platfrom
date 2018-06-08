package com.cic.entity.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_books_grade")
public class SysBooksGrade {
    @Id
    private String uuid;

    @Column(name = "sys_books_uuid")
    private String sysBooksUuid;

    @Column(name = "sys_user_uuid")
    private String sysUserUuid;

    private Integer grade;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return sys_books_uuid
     */
    public String getSysBooksUuid() {
        return sysBooksUuid;
    }

    /**
     * @param sysBooksUuid
     */
    public void setSysBooksUuid(String sysBooksUuid) {
        this.sysBooksUuid = sysBooksUuid;
    }

    /**
     * @return sys_user_uuid
     */
    public String getSysUserUuid() {
        return sysUserUuid;
    }

    /**
     * @param sysUserUuid
     */
    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }

    /**
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}