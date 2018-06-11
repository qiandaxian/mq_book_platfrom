package com.cic.dao;

import com.cic.config.dao.Mapper;
import com.cic.entity.dto.*;
import com.cic.entity.po.SysBooks;
import com.cic.entity.po.SysBooksBorrowDetail;
import com.cic.entity.vo.*;

import java.util.List;
import java.util.Map;

public interface SysBooksMapper extends Mapper<SysBooks> {
    List<MyBorrowDTO> getMyBorrowList(Map param);

    List<SysBooksBorrowDetail> getUserBorrowListByBookId(Map param);

    Integer getGradeByBookId(Map param);

    Integer getMyBorrowTotleByBookId(Map param);

    SysBooks getSysBooksByIsbn(Map param);

    List<BookBorrowListDTO> getBookBorrowList(BookBorrowListVo vo);

    List<BookBorrowInfoDTO> getBookBorrowInfo(BookBorrowInfoVo vo);

    List<UserBorrowListDTO> getUserBorrowList(UserListVo vo);

    List<UserBorrowInfoDTO> getUserBorrowInfo(UserBorrowInfoVo vo);

    List<BookListDTO> getBookList(BookListVo vo);
}