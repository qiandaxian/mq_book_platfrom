package com.cic.module;
import com.alibaba.fastjson.JSONObject;
import com.cic.config.dao.Result;
import com.cic.config.dao.ResultGenerator;
import com.cic.entity.dto.*;
import com.cic.entity.po.SysBooks;
import com.cic.entity.po.SysBooksBorrowDetail;
import com.cic.entity.po.SysBooksGrade;
import com.cic.entity.po.SysUser;
import com.cic.entity.vo.AddBooksVo;
import com.cic.entity.vo.AddGradeVo;
import com.cic.entity.vo.BookBorrowInfoVo;
import com.cic.entity.vo.BookBorrowListVo;
import com.cic.service.SysBooksBorrowDetailService;
import com.cic.service.SysBooksGradeService;
import com.cic.service.SysBooksService;
import com.cic.service.SysUserService;
import com.cic.utils.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cic.service.CommonService.*;

/**
* Created by daxian on 2018/06/01.
*/
@RestController
@RequestMapping("/api/sysBooks")
public class SysBooksController {
	Logger logger = LoggerFactory.getLogger(SysBooksController.class);

    @Resource
	SysBooksService sysBooksService;
    @Resource
    SysUserService sysUserService;
    @Resource
    SysBooksBorrowDetailService sysBooksBorrowDetailService;
	@Resource
    SysBooksGradeService gradeService;

    @PostMapping("/addBooks")
    public Result create(@RequestBody AddBooksVo addBooksVo,@RequestHeader("Authorization") String sysUserId) {
        Result result = null;
        try {

			SysBooks sysBooks =convertAddBooksVoToSysBooks(addBooksVo);
			SysUser user = sysUserService.findById(sysUserId);

			SysBooks existBook = sysBooksService.getSysBookByIsbnAndCompanyId(sysBooks.getIsbn13(),user.getCompanyId());
			//不存在的书做新增，存在的书，叠加数量
			if (existBook == null){
				sysBooks.setCompanyId(user.getCompanyId());
				sysBooksService.save(sysBooks);
			}else {
				existBook.setTotle(existBook.getTotle()+sysBooks.getTotle());
				existBook.setCompanyId(user.getCompanyId());
				sysBooksService.update(existBook);
			}

			result = ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		}
		logger.info("添加书籍返回：{}",JSONObject.toJSON(result));
        return result;
    }

	@RequestMapping("/getBooks")
	public Result getBooks(String bookIsbn,@RequestHeader("Authorization") String sysUserId) throws Exception {
		Result result = null;


			if(bookIsbn==null||!(bookIsbn.length()==13||bookIsbn.length()==10)){
				return ResultGenerator.genFailResult("Isbn码格式有误");
			}

			SysBooks books = getBooksByIsbn(bookIsbn,sysUserId);
			if(books!=null) {
				Integer grade = sysBooksService.getGradeByBookId(books.getUuid());
				BookInfoDTO bookInfoDTO = convertSysBookToBookDTO(books,grade);
				result = ResultGenerator.genSuccessResult(bookInfoDTO);
			}else {
				result = ResultGenerator.genSuccessResult("暂未添加该书籍！");
			}

		logger.info("书籍详情返回信息：{}",JSONObject.toJSONString(result));

		return result;
	}


	private SysBooks getBooksByIsbn(String bookIsbn,String sysUserId) throws Exception {
		SysUser sysUser = sysUserService.findById(sysUserId);
		SysBooks books = sysBooksService.getSysBookByIsbnAndCompanyId(bookIsbn,sysUser.getCompanyId());
		return books;
	}

	@RequestMapping("/bookStatus")
	public Result getBookStatus(String bookIsbn,@RequestHeader("Authorization") String sysUserId) throws Exception {
		Result result = null;

		if(bookIsbn==null||!(bookIsbn.length()==13||bookIsbn.length()==10)){
			return ResultGenerator.genFailResult("Isbn码格式有误");
		}

		BookStatusDTO bookStatusDTO = getBookStatusDTO(bookIsbn, sysUserId);

		result = ResultGenerator.genSuccessResult(bookStatusDTO);

		logger.info("书籍状态返回信息：{}",JSONObject.toJSONString(result));

		return result;
	}

	private BookStatusDTO getBookStatusDTO(String bookIsbn,String sysUserId) throws Exception {
		SysBooks books = getBooksByIsbn(bookIsbn,sysUserId);

		BookStatusDTO bookStatusDTO = new BookStatusDTO();
		if(books!=null) {
			SysUser sysUser = sysUserService.findById(sysUserId);
			Integer borrowTotle =  sysBooksService.getMyBorrowTotleByBookId(books.getUuid(),sysUser.getCompanyId());
			if (books.getTotle()>borrowTotle){
				bookStatusDTO.setStatus(BookStatusDTO.BOOK_STATUS_EXITS);
			}else {
				bookStatusDTO.setStatus(BookStatusDTO.BOOK_STATUS_EXITS_NO_STOCK);
			}

			List<SysBooksBorrowDetail> sysBooksBorrowDetails = sysBooksService.getMyBorrowDetailByBookId(sysUser.getUuid(),books.getUuid());

			if (sysBooksBorrowDetails!=null&&sysBooksBorrowDetails.size()>0){
				bookStatusDTO.setBorrowType(BookStatusDTO.BORROT_TYPE_TRUE);
			}else {
				bookStatusDTO.setBorrowType(BookStatusDTO.BORROT_TYPE_FALSE);
			}

		}else {
			bookStatusDTO.setStatus(BookStatusDTO.BOOK_STATUS_NOT_EXITS);
		}
		return bookStatusDTO;
	}

	@RequestMapping("/myBorrow")
    public Result list(@RequestHeader("Authorization") String sysUserId) {
    	Result result = null;

		List<MyBorrowDTO> data = sysBooksService.getMyBorrowList(sysUserId);
		result = ResultGenerator.genSuccessResult(data);

		logger.info("我的借阅返回信息：{}",JSONObject.toJSON(result));
        return result;        
    }

	@PostMapping("/borrowBooks")
	public Result borrowBooks(@RequestBody Map body,@RequestHeader("Authorization") String sysUserId) throws Exception {
		Result result = ResultGenerator.genFailResult();

		SysBooks sysBooks = getBooksByIsbn(body.get("bookIsbn").toString(),sysUserId);
		SysUser sysUser = sysUserService.findById(sysUserId);

		BookStatusDTO bookStatusDTO = getBookStatusDTO(sysBooks.getIsbn13(), sysUserId);

		if(sysBooks!=null&&sysUser!=null&&bookStatusDTO!=null&&BookStatusDTO.BOOK_STATUS_EXITS.equals(bookStatusDTO.getStatus())) {
			saveBorrowDetail(sysBooks, sysUser);
			result = ResultGenerator.genSuccessResult();
		}

		logger.info("借书接口返回信息：{}",JSONObject.toJSON(result));
		return result;
	}

	private void saveBorrowDetail(SysBooks sysBooks, SysUser sysUser) throws Exception {
		SysBooksBorrowDetail sysBooksBorrowDetail = new SysBooksBorrowDetail();
		sysBooksBorrowDetail.setUuid(UUIDGenerator.genUuidStr());
		sysBooksBorrowDetail.setCreateTime(new Date());
		sysBooksBorrowDetail.setSysBooksUuid(sysBooks.getUuid());
		sysBooksBorrowDetail.setSysUserUuid(sysUser.getUuid());
		sysBooksBorrowDetail.setBorrowStatus(SysBooksBorrowDetail.BORROW_STATUS_TRUE);
		sysBooksBorrowDetailService.save(sysBooksBorrowDetail);
	}

	@PostMapping("/returnBooks")
	public Result returnBooks(@RequestBody Map body,@RequestHeader("Authorization") String sysUserId) throws Exception {
		Result result = ResultGenerator.genFailResult();

		SysBooks sysBooks = getBooksByIsbn(body.get("bookIsbn").toString(),sysUserId);
		SysUser sysUser = sysUserService.findById(sysUserId);

		if(sysBooks!=null&&sysUser!=null) {
			List<SysBooksBorrowDetail> sysBooksBorrowDetails = sysBooksService.getMyBorrowDetailByBookId(sysUser.getUuid(),sysBooks.getUuid());

			if (sysBooksBorrowDetails!=null && sysBooksBorrowDetails.size()>0){
				result = updateBorrowDetail(sysBooksBorrowDetails);
			}else {
				result=ResultGenerator.genFailResult("该书借书信息不存在！");
			}
		}



		logger.info("还书接口返回信息：{}",JSONObject.toJSON(result));
		return result;
	}

	private Result updateBorrowDetail(List<SysBooksBorrowDetail> sysBooksBorrowDetails) throws Exception {
		Result result;
		SysBooksBorrowDetail backBook = sysBooksBorrowDetails.get(0);
		backBook.setBorrowStatus(SysBooksBorrowDetail.BORROW_STATUS_FALSE);
		backBook.setReturnTime(new Date());
		sysBooksBorrowDetailService.update(backBook);
		result = ResultGenerator.genSuccessResult();
		return result;
	}

	@PostMapping("/addGrade")
	public Result addGrade(@RequestBody AddGradeVo addGradeVo, @RequestHeader("Authorization") String sysUserId) throws Exception {
		Result result = ResultGenerator.genSuccessResult();

		SysUser sysUser = sysUserService.findById(sysUserId);
		SysBooks sysBooks = getBooksByIsbn(addGradeVo.getBookIsbn(),sysUserId);

		if(sysBooks!=null&&sysUser!=null&&!StringUtils.isEmpty(addGradeVo.getGrade())) {
			saveBookGrade(addGradeVo, sysUser, sysBooks);
		}else {
			result = ResultGenerator.genFailResult("参数错误!");
		}

		logger.info("评分接口返回信息：{}",JSONObject.toJSON(result));
		return result;
	}

	private void saveBookGrade(@RequestBody AddGradeVo addGradeVo, SysUser sysUser, SysBooks sysBooks) throws Exception {
		SysBooksGrade booksGrade = new SysBooksGrade();
		booksGrade.setUuid(UUIDGenerator.genUuidStr());
		booksGrade.setCreateTime(new Date());
		booksGrade.setGrade(Integer.parseInt(addGradeVo.getGrade()));
		booksGrade.setSysBooksUuid(sysBooks.getUuid());
		booksGrade.setSysUserUuid(sysUser.getUuid());
		gradeService.save(booksGrade);
	}


	/**
	 * 3.4借阅管理>书籍借阅列表
	 * @return
	 */
	@PostMapping("/bookBorrowList")
	public Result getBookBorrowList(@RequestBody BookBorrowListVo vo){
		Result result = null;
		PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
		List data = sysBooksService.getBookBorrowList(vo);
		PageInfo<BookBorrowListDTO> pageInfo = new PageInfo<BookBorrowListDTO>(data);
		result = ResultGenerator.genSuccessResult(pageInfo);
		logger.info("借阅管理>书籍借阅列表返回信息：{}",JSONObject.toJSON(result));
		return result;
	}

	/**
	 * 3.5借阅管理>书籍借阅>详情
	 * @param vo
	 * @return
	 */
	@PostMapping("/bookBorrowInfo")
	public Result getBookBorrowInfo(@RequestBody BookBorrowInfoVo vo) {
		Result result = null;
		PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
		List<BookBorrowInfoDTO> data = sysBooksService.getBookBorrowInfo(vo);
		PageInfo<BookBorrowInfoDTO> pageInfo = new PageInfo<BookBorrowInfoDTO>(data);
		result = ResultGenerator.genSuccessResult(pageInfo);
		logger.info("借阅管理>书籍借阅>详情返回信息：{}",JSONObject.toJSON(result));
		return result;
	}
}


