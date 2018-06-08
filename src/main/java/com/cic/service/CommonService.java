package com.cic.service;

import com.cic.entity.dto.BookInfoDTO;
import com.cic.entity.dto.NativeDTO;
import com.cic.entity.dto.UserInfoDTO;
import com.cic.entity.po.SysBooks;
import com.cic.entity.po.SysCompany;
import com.cic.entity.po.SysUser;
import com.cic.entity.vo.AddBooksVo;
import com.cic.utils.UUIDGenerator;


import static com.cic.utils.DaxianStringUtils.arrayToStringWithSplit;

public class CommonService  {
    public static SysBooks convertAddBooksVoToSysBooks(AddBooksVo addBooksVo){
        SysBooks sysBooks = new SysBooks();
        sysBooks.setAuthor(arrayToStringWithSplit(addBooksVo.getData().getAuthor(),","));
        sysBooks.setAuthorIntro(addBooksVo.getData().getAuthor_intro());
        sysBooks.setImagesLarge(addBooksVo.getData().getImages().get("large").toString());
        sysBooks.setImagesMedium(addBooksVo.getData().getImages().get("medium").toString());
        sysBooks.setImagesSmall(addBooksVo.getData().getImages().get("small").toString());
        sysBooks.setIsbn10(addBooksVo.getData().getIsbn10());
        sysBooks.setIsbn13(addBooksVo.getData().getIsbn13());
        sysBooks.setPublisher(addBooksVo.getData().getPublisher());
        sysBooks.setPrice(addBooksVo.getData().getPrice());
        sysBooks.setSummary(addBooksVo.getData().getSummary());
        sysBooks.setTagImage(addBooksVo.getData().getImage());
        sysBooks.setTitle(addBooksVo.getData().getTitle());
        sysBooks.setTranslator(arrayToStringWithSplit(addBooksVo.getData().getTranslator(),","));
        sysBooks.setUuid(UUIDGenerator.genUuidStr());
        sysBooks.setTagOriginTitle(addBooksVo.getData().getOrigin_title());
        sysBooks.setTotle(Integer.parseInt(addBooksVo.getAddNumber()));
        return sysBooks;
    }

    public static BookInfoDTO convertSysBookToBookDTO(SysBooks sysBooks,Integer grade){
        BookInfoDTO bookInfoDTO = new BookInfoDTO();
        bookInfoDTO.setUuid(sysBooks.getUuid());
        bookInfoDTO.setIsbn10(sysBooks.getIsbn10());
        bookInfoDTO.setIsbn13(sysBooks.getIsbn13());
        bookInfoDTO.setTitle(sysBooks.getTitle());
        bookInfoDTO.setAuthor(sysBooks.getAuthor());
        bookInfoDTO.setTagImage(sysBooks.getTagImage());
        bookInfoDTO.setSummary(sysBooks.getSummary());
        bookInfoDTO.setImagesLarge(sysBooks.getImagesLarge());
        bookInfoDTO.setTranslator(sysBooks.getTranslator());
        bookInfoDTO.setGrade(grade+"");
        return bookInfoDTO;
    }

    public static NativeDTO convertSysCompanyToNativeDTO(SysCompany sysCompany){
        NativeDTO nativeDTO = new NativeDTO();
        nativeDTO.setId(sysCompany.getUuid());
        nativeDTO.setValue(sysCompany.getCompanyName());
        return nativeDTO;
    }

    public static UserInfoDTO convertSysuserToUserInfoDTO(SysUser sysUser){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName(sysUser.getUserName());
        userInfoDTO.setCompanyId(sysUser.getCompanyId());
        return userInfoDTO;
    }
}
