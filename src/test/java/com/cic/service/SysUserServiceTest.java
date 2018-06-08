package com.cic.service;

import com.cic.entity.po.SysUser;
import com.cic.utils.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserServiceTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void getUser(){
        SysUser user = sysUserService.findBy("openId","111111");
    }

    @Test
    public void  saveUser(){
//        SysUser sysUser = new SysUser();
//        sysUser.setOpenId("111111");
//        sysUser.setSessionKey("222222");
//        sysUser.setUuid(UUIDGenerator.genUuidStr());
//        sysUser.setCreateTime(new Date());
//        try {
//            sysUserService.save(sysUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}