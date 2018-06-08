package com.cic.entity.dto;

import lombok.Data;

@Data
public class BookInfoDTO {
    private String uuid;

    private String isbn10;

    private String isbn13;

    private String title;

    private String author;

    private String tagImage;

    private String summary;

    private String imagesLarge;

    private String translator;

    private String grade;

}
