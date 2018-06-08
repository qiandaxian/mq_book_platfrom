package com.cic.service.impl;

import com.cic.dao.SysUserMapper;
import com.cic.entity.po.SysUser;
import com.cic.service.SysUserService;
import com.cic.config.dao.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by daxian on 2018/06/01.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

}
