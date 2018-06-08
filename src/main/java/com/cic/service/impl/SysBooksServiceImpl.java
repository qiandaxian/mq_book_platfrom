package com.cic.service.impl;

import com.cic.dao.SysBooksMapper;
import com.cic.entity.dto.MyBorrowDTO;
import com.cic.entity.po.SysBooks;
import com.cic.entity.po.SysBooksBorrowDetail;
import com.cic.service.SysBooksService;
import com.cic.config.dao.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by daxian on 2018/06/01.
 */
@Service
@Transactional
public class SysBooksServiceImpl extends AbstractService<SysBooks> implements SysBooksService {
    @Resource
    private SysBooksMapper sysBooksMapper;

    @Override
    public List<MyBorrowDTO> getMyBorrowList(String sysUserId) {
        Map param = new HashMap();
        param.put("sysUserId",sysUserId);
        param.put("borrowStatus",SysBooksBorrowDetail.BORROW_STATUS_TRUE);
        return sysBooksMapper.getMyBorrowList(param);
    }

    @Override
    public List<SysBooksBorrowDetail> getMyBorrowDetailByBookId(String sysUserId, String bookId) {
        Map param = new HashMap();
        param.put("sysUserId",sysUserId);
        param.put("sysBooksId",bookId);
        param.put("borrowStatus",SysBooksBorrowDetail.BORROW_STATUS_TRUE);
        return sysBooksMapper.getUserBorrowListByBookId(param);
    }

    @Override
    public Integer getGradeByBookId(String sysBookId) {
        Map param = new HashMap();
        param.put("sysBooksId",sysBookId);
        return sysBooksMapper.getGradeByBookId(param);
    }

    @Override
    public Integer getMyBorrowTotleByBookId(String sysBookId, String companyId) {
        Map param = new HashMap();
        param.put("sysBooksId",sysBookId);
        param.put("companyId",companyId);
        return sysBooksMapper.getMyBorrowTotleByBookId(param);
    }

    @Override
    public SysBooks getSysBookByIsbnAndCompanyId(String bookIsbn, String companyId) {
        Map param = new HashMap();
        param.put("bookIsbn",bookIsbn);
        param.put("companyId",companyId);
        return sysBooksMapper.getSysBooksByIsbn(param);
    }


}
