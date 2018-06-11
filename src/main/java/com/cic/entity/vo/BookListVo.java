package com.cic.entity.vo;

import lombok.Data;

@Data
public class BookListVo extends PageBaseVo {
     private String bookIsbn;
     private String bookName;
     private String stockNumberGt;
     private String companyId;
}
