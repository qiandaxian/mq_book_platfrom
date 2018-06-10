package com.cic.entity.dto;

import lombok.Data;

@Data
public class BookBorrowInfoDTO {
    private String borrowUserName;
    private String borrowDate;
    private String isReturn;
    private String returnDate;
}
