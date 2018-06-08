package com.cic.service.impl;

import com.cic.dao.SysCompanyMapper;
import com.cic.entity.po.SysCompany;
import com.cic.service.SysCompanyService;
import com.cic.config.dao.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by daxian on 2018/06/06.
 */
@Service
@Transactional
public class SysCompanyServiceImpl extends AbstractService<SysCompany> implements SysCompanyService {
    @Resource
    private SysCompanyMapper sysCompanyMapper;

}
