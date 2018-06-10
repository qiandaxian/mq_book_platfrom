package com.cic.service;
import com.cic.entity.dto.BookBorrowListDTO;
import com.cic.entity.dto.MyBorrowDTO;
import com.cic.entity.po.SysBooks;
import com.cic.config.dao.Service;
import com.cic.entity.po.SysBooksBorrowDetail;
import com.cic.entity.vo.BookBorrowListVo;

import java.util.List;
import java.util.Map;


/**
 * Created by daxian on 2018/06/01.
 */
public interface SysBooksService extends Service<SysBooks> {

    List<MyBorrowDTO> getMyBorrowList(String sysUserId);

    List<SysBooksBorrowDetail> getMyBorrowDetailByBookId(String sysUserId,String bookId);

    Integer getGradeByBookId(String sysBookId);

    Integer getMyBorrowTotleByBookId(String sysBookId,String companyId);

    SysBooks getSysBookByIsbnAndCompanyId(String bookIsbn,String companyId);

    List<BookBorrowListDTO> getBookBorrowList(BookBorrowListVo vo);

}
