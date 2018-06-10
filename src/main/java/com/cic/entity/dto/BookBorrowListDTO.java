package com.cic.entity.dto;

import lombok.Data;

@Data
public class BookBorrowListDTO {
    private String bookId;
    private String title;
    private String isbn13;
    private String borrowTotle;
    private String lastBorrowUserName;
    private String lastBorrowDate;
    private String companyId;
}
