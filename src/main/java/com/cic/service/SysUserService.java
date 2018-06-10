package com.cic.service;
import com.cic.entity.dto.UserListDTO;
import com.cic.entity.po.SysUser;
import com.cic.config.dao.Service;
import com.cic.entity.vo.UserListVo;

import java.util.List;


/**
 * Created by daxian on 2018/06/01.
 */
public interface SysUserService extends Service<SysUser> {
    List<UserListDTO> getUserListByVo(UserListVo vo);
}
