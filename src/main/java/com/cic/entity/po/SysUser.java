package com.cic.entity.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {

    public static final short USER_INFO_EXITS = 1;
    public static final short USER_INFO_NOT_EXITS = 0;

    @Id
    private String uuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "user_input_status")
    private Short userInputStatus;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "is_admin")
    private Short isAdmin;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return session_key
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sessionKey
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * @return user_input_status
     */
    public Short getUserInputStatus() {
        return userInputStatus;
    }

    /**
     * @param userInputStatus
     */
    public void setUserInputStatus(Short userInputStatus) {
        this.userInputStatus = userInputStatus;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return is_admin
     */
    public Short getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin
     */
    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}