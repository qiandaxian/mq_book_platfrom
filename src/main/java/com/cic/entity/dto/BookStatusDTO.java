package com.cic.entity.dto;

import lombok.Data;

@Data
public class BookStatusDTO {
    public static final String BORROT_TYPE_TRUE="1";
    public static final String BORROT_TYPE_FALSE="0";

    public static final String BOOK_STATUS_NOT_EXITS="0";
    public static final String BOOK_STATUS_EXITS="1";
    public static final String BOOK_STATUS_EXITS_NO_STOCK="2";


    private String status;
    private String borrowType="0";
}
