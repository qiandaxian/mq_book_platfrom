package com.cic.entity.vo;

import lombok.Data;

@Data
public class BookBorrowInfoVo extends PageBaseVo {
    private String bookId;
    private String bookBorrowUserName;
    private String bookBorrowStartDate;
    private String bookBorrowEndDate;
    private String bookBorrowNumberGt;
    private String isReturn;
}
