package com.cic.service.impl;

import com.cic.dao.SysBooksBorrowDetailMapper;
import com.cic.entity.po.SysBooksBorrowDetail;
import com.cic.service.SysBooksBorrowDetailService;
import com.cic.config.dao.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;


/**
 * Created by daxian on 2018/06/01.
 */
@Service
@Transactional
public class SysBooksBorrowDetailServiceImpl extends AbstractService<SysBooksBorrowDetail> implements SysBooksBorrowDetailService {
    @Resource
    private SysBooksBorrowDetailMapper sysBooksBorrowDetailMapper;



}
