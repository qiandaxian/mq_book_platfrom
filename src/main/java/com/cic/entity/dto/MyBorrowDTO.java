package com.cic.entity.dto;

import lombok.Data;


@Data
public class MyBorrowDTO extends BookInfoDTO {

    private String borrowTime;

    private Integer borrowStatus;

    private String returnTime;
}
