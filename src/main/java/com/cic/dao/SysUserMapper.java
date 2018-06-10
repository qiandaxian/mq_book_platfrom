package com.cic.dao;

import com.cic.config.dao.Mapper;
import com.cic.entity.dto.UserListDTO;
import com.cic.entity.po.SysUser;
import com.cic.entity.vo.UserListVo;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    List<UserListDTO> getUserListByVo(UserListVo vo);
}