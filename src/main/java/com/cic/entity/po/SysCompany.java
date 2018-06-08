package com.cic.entity.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_company")
public class SysCompany {
    @Id
    private String uuid;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "company_scope")
    private String companyScope;

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
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * @return company_scope
     */
    public String getCompanyScope() {
        return companyScope;
    }

    /**
     * @param companyScope
     */
    public void setCompanyScope(String companyScope) {
        this.companyScope = companyScope;
    }
}