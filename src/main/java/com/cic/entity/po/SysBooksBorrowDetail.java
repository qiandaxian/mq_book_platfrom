package com.cic.entity.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_books_borrow_detail")
public class SysBooksBorrowDetail {
    public static final int BORROW_STATUS_TRUE=1;
    public static final int BORROW_STATUS_FALSE=0;

    @Id
    private String uuid;

    @Column(name = "sys_user_uuid")
    private String sysUserUuid;

    @Column(name = "sys_books_uuid")
    private String sysBooksUuid;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0：归还 1：已借
     */
    @Column(name = "borrow_status")
    private Integer borrowStatus;

    @Column(name = "return_time")
    private Date returnTime;

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

    /**
     * 获取0：借出 1：已归还
     *
     * @return borrow_status - 0：借出 1：已归还
     */
    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    /**
     * 设置0：借出 1：已归还
     *
     * @param borrowStatus 0：借出 1：已归还
     */
    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    /**
     * @return return_time
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * @param returnTime
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}