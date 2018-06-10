package com.cic.entity.vo;

import lombok.Data;

@Data
public class BookBorrowListVo extends PageBaseVo{
    private String bookIsbn;
    private String bookName;
    private String company_id;
}
