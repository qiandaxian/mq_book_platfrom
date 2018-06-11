package com.cic.entity.vo;

import lombok.Data;

@Data
public class UserBorrowInfoVo extends PageBaseVo {
    private String userId;
    private String companyId;
    private String bookName;
    private String bookIsbn;
    private String bookBorrowStartDate;
    private String bookBorrowEndDate;
    private String bookBorrowNumberGt;
    private String isReturn;
}
