package com.cic.entity.dto;

import lombok.Data;

@Data
public class BookListDTO {
    private String bookId;
    private String bookName;
    private String bookIsbn;
    private String total;
    private String stockNumber;
    private String grade;
}
