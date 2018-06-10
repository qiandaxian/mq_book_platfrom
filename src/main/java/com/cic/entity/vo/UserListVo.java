package com.cic.entity.vo;

import lombok.Data;

@Data
public class UserListVo extends PageBaseVo {
    private String openId;
    private String userName;
    private String company_id;
}
