package com.cic.module;
import com.alibaba.fastjson.JSONObject;
import com.cic.config.dao.Result;
import com.cic.config.dao.ResultGenerator;
import com.cic.entity.dto.NativeDTO;
import com.cic.entity.dto.UserListDTO;
import com.cic.entity.dto.WxLoginResult;
import com.cic.entity.po.SysBooks;
import com.cic.entity.po.SysCompany;
import com.cic.entity.po.SysUser;
import com.cic.entity.vo.*;
import com.cic.service.SysCompanyService;
import com.cic.service.SysUserService;
import com.cic.utils.UUIDGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static com.cic.service.CommonService.convertSysCompanyToNativeDTO;
import static com.cic.service.CommonService.convertSysuserToUserInfoDTO;

/**
* Created by daxian on 2018/06/01.
*/
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {

	Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@Resource
    private SysUserService sysUserService;
    @Autowired
    private CustomTrust customTrust;
    @Value("${mq.app.id}")
    private String appId;
	@Value("${mq.app.script}")
	private String script;
	@Resource
	private SysCompanyService sysCompanyService;

    @RequestMapping("/login")
    public Result login(String code) throws Exception {

		WxLoginResult wxLoginResult = null;

		if(!StringUtils.isEmpty(code)) {
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + script + "&js_code=" + code + "&grant_type=authorization_code";
			Request request1 = new Request.Builder()
					.url(url)
					.build();

				Response response = customTrust.getClient().newCall(request1).execute();
				wxLoginResult = JSONObject.parseObject(response.body().string(),WxLoginResult.class);

				if(isSuccessReturn(wxLoginResult)){

					SysUser sysUser = sysUserService.findBy("openId",wxLoginResult.getOpenid());
					//新建用户
					if (sysUser == null){
						sysUser = getNewSysUser(wxLoginResult);
					}

					//返回用户token
					Map dataMap = new HashMap();
					dataMap.put("token",sysUser.getUuid());
					dataMap.put("isAdmin",sysUser.getIsAdmin()+"");

					logger.info("用户登陆返回信息：{}",dataMap.toString());

					return ResultGenerator.genSuccessResult(dataMap);
				}

		}

		return ResultGenerator.genFailResult("身份验证有误!");
	}

	private boolean isSuccessReturn(WxLoginResult wxLoginResult) {
		return wxLoginResult!=null&&wxLoginResult.getErrcode()==null&&StringUtils.isEmpty(wxLoginResult.getErrmsg());
	}

	private SysUser getNewSysUser(WxLoginResult wxLoginResult) throws Exception {
		SysUser sysUser;
		sysUser = new SysUser();
		sysUser.setOpenId(wxLoginResult.getOpenid());
		sysUser.setSessionKey(wxLoginResult.getSession_key());
		sysUser.setUuid(UUIDGenerator.genUuidStr());
		sysUser.setCreateTime(new Date());
		sysUser.setIsAdmin((short)0);
		sysUserService.save(sysUser);
		return sysUser;
	}

	@RequestMapping("/userInfoInputStatus")
	public Result getUserInfoInputStatus(@RequestHeader("Authorization") String sysUserId) throws Exception {
		SysUser sysUser = sysUserService.findById(sysUserId);
		Map data = new HashMap();
		data.put("exitsUserInfo",sysUser.getUserInputStatus()==1?true:false);

		Result result = ResultGenerator.genSuccessResult(data);
		logger.info("用户基础信息录入状态查询返回:{}",JSONObject.toJSONString(result));
		return result;
	}

	@PostMapping("/addUserInfo")
	public Result addUserInfo(@RequestBody AddUserInfoVo addUserInfoVo, @RequestHeader("Authorization") String sysUserId) throws Exception {
		SysUser sysUser = sysUserService.findById(sysUserId);
		sysUser.setUserName(addUserInfoVo.getUserName());
		sysUser.setCompanyId(addUserInfoVo.getCompanyId());
		sysUser.setUserInputStatus(SysUser.USER_INFO_EXITS);
		sysUserService.update(sysUser);

		Result result = ResultGenerator.genSuccessResult();
		logger.info("用户基础信息录入接口返回:{}",JSONObject.toJSONString(result));
		return result;
	}

	@RequestMapping("/userInfo")
	public Result getUserInfo(@RequestHeader("Authorization") String sysUserId) throws Exception {
		SysUser sysUser = sysUserService.findById(sysUserId);
		Result result = ResultGenerator.genSuccessResult(convertSysuserToUserInfoDTO(sysUser));
		logger.info("获取用户信息接口返回：{}",result);
		return result;
	}

	@RequestMapping("/getCompany")
	public Result getCompanyList() throws Exception {
		List<SysCompany> sysCompanyList = sysCompanyService.findAll();
		List<NativeDTO> data = new ArrayList();
		sysCompanyList.forEach(sysCompany -> data.add(convertSysCompanyToNativeDTO(sysCompany)));
		return ResultGenerator.genSuccessResult(data);
	}


	/**
	 * 3.0后台管理员登陆接口
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/adminLogin")
	public Result adminLogin(@RequestBody AdminLoginVo vo) throws Exception {
		Result result = null;
    	SysUser loginUser = sysUserService.findBy("account",vo.getUserAccount());
    	if (loginUser!=null && loginUser.getPassword()!=null && loginUser.getPassword().equals(vo.getUserPassword())){
			Map data = new HashMap();
			data.put("token",loginUser.getUuid());
    		result = ResultGenerator.genSuccessResult(data);
		}else {
			result = ResultGenerator.genFailResult("用户不存在或者密码错误!");
		}
		logger.info("后台管理员登陆接口:{}",JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 3.1用户列表接口
	 * @param vo
	 * @return
	 */
	@PostMapping("/userList")
	public Result getUserList(@RequestBody UserListVo vo){
		Result result = null;
		PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
		List sysUserList = sysUserService.getUserListByVo(vo);
		PageInfo<UserListDTO> pageInfo = new PageInfo<UserListDTO>(sysUserList);
		result = ResultGenerator.genSuccessResult(pageInfo);
		logger.info("3.1用户列表接口返回:{}",JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 3.2用户删除接口
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delete")
	public Result deleteUser(@RequestBody UserDeleteVo vo) throws Exception {
		Result result =  ResultGenerator.genSuccessResult();
		vo.getUserId().forEach(id-> {
			try {
				sysUserService.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		logger.info("用户删除接口返回：{}",JSONObject.toJSONString(result));
		return result;
	}

	/**
	 * 3.3用户管理>修改
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/edit")
	public Result editUser(@RequestBody EditUserVo vo) throws Exception {
		Result result = null;
		SysUser editUser = sysUserService.findById(vo.getUserId());
		if (editUser != null){
			editUser.setCompanyId(vo.getCompanyId());
			editUser.setUserName(vo.getUserName());
			sysUserService.save(editUser);
		}else {
			throw new Exception();
		}
		logger.info("用户删除接口返回：{}",JSONObject.toJSONString(result));
		return result;
	}
}
