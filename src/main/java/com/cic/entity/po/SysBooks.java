package com.cic.entity.po;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "sys_books")
public class SysBooks {
    @Id
    private String uuid;

    private String title;

    private String author;

    @Column(name = "tag_origin_title")
    private String tagOriginTitle;

    @Column(name = "tag_image")
    private String tagImage;

    private String translator;

    @Column(name = "images_small")
    private String imagesSmall;

    @Column(name = "images_large")
    private String imagesLarge;

    @Column(name = "images_medium")
    private String imagesMedium;

    private String publisher;

    private String isbn10;

    private String isbn13;

    @Column(name = "author_intro")
    private String authorIntro;

    private String summary;

    private String price;

    private Integer totle;

    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return tag_origin_title
     */
    public String getTagOriginTitle() {
        return tagOriginTitle;
    }

    /**
     * @param tagOriginTitle
     */
    public void setTagOriginTitle(String tagOriginTitle) {
        this.tagOriginTitle = tagOriginTitle;
    }

    /**
     * @return tag_image
     */
    public String getTagImage() {
        return tagImage;
    }

    /**
     * @param tagImage
     */
    public void setTagImage(String tagImage) {
        this.tagImage = tagImage;
    }

    /**
     * @return translator
     */
    public String getTranslator() {
        return translator;
    }

    /**
     * @param translator
     */
    public void setTranslator(String translator) {
        this.translator = translator;
    }

    /**
     * @return images_small
     */
    public String getImagesSmall() {
        return imagesSmall;
    }

    /**
     * @param imagesSmall
     */
    public void setImagesSmall(String imagesSmall) {
        this.imagesSmall = imagesSmall;
    }

    /**
     * @return images_large
     */
    public String getImagesLarge() {
        return imagesLarge;
    }

    /**
     * @param imagesLarge
     */
    public void setImagesLarge(String imagesLarge) {
        this.imagesLarge = imagesLarge;
    }

    /**
     * @return images_medium
     */
    public String getImagesMedium() {
        return imagesMedium;
    }

    /**
     * @param imagesMedium
     */
    public void setImagesMedium(String imagesMedium) {
        this.imagesMedium = imagesMedium;
    }

    /**
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * @param isbn10
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @return isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * @param isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @return author_intro
     */
    public String getAuthorIntro() {
        return authorIntro;
    }

    /**
     * @param authorIntro
     */
    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return totle
     */
    public Integer getTotle() {
        return totle;
    }

    /**
     * @param totle
     */
    public void setTotle(Integer totle) {
        this.totle = totle;
    }
}