package com.cic.entity.dto;

import lombok.Data;

@Data
public class UserBorrowListDTO extends UserListDTO {
    private String borrowTotal;
    private String unReturnTotal;
    private String lastBorrowBookName;
    private String lastBorrowBookDate;
}
