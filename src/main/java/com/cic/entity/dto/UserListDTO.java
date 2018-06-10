package com.cic.entity.dto;

import lombok.Data;

@Data
public class UserListDTO {
    private String userId;
    private String userName;
    private  String openId;
    private String createTime;
    private String companyName;
    private String companyId;
}
