package com.cic.entity.dto;

import lombok.Data;

@Data
public class WxLoginResult {
    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
