package com.cic.service.impl;

import com.cic.dao.SysBooksGradeMapper;
import com.cic.entity.po.SysBooksGrade;
import com.cic.service.SysBooksGradeService;
import com.cic.config.dao.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by daxian on 2018/06/01.
 */
@Service
@Transactional
public class SysBooksGradeServiceImpl extends AbstractService<SysBooksGrade> implements SysBooksGradeService {
    @Resource
    private SysBooksGradeMapper sysBooksGradeMapper;

}
