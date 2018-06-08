package com.cic.entity.vo;

import lombok.Data;

@Data
public class AddBooksVo {
    private IsbnInfoVo data;
    private String addNumber = "0";
}
