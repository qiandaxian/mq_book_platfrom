package com.cic.entity.dto;

import lombok.Data;

@Data
public class UserBorrowInfoDTO {
    private String bookName;
    private String borrowDate;
    private String isReturn;
    private String returnDate;
}
