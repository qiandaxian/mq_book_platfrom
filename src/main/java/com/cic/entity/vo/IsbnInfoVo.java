package com.cic.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class IsbnInfoVo {
    private List tags;
    private List author;
    private List translator;
    private Map images;
    private String image;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String author_intro;
    private String summary;
    private String price;
    private String origin_title;
}
