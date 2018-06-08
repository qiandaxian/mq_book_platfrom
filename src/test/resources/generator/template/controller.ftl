package ${basePackage}.module;
import ${basePackage}.config.dao.Result;
import ${basePackage}.config.dao.ResultGenerator;
import ${basePackage}.entity.po.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/create")
    public Result create(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        Result result = null;
        try {
			${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
			result = ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		}
        return result;
    }

    @PostMapping("/delete")
    public Result delete(String id) {
        Result result = null;
        try {
			${modelNameLowerCamel}Service.deleteById(id);
			result = ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		}
        return result;
    }

    @PostMapping("/update")
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        Result result = null;
        try {
			${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
			result = ResultGenerator.genSuccessResult();
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		}
        return result;
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        Result result = null;
        try {
			${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
			result = ResultGenerator.genSuccessResult(${modelNameLowerCamel});
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		}
        return result;
    }

    @PostMapping("/list")
    public Result list(Integer page, Integer size) {
    	Result result = null;
    	try {
	    	PageHelper.startPage(page, size);
	       	List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
	        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
	        result = ResultGenerator.genSuccessResult(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultGenerator.genFailResult();
		} 
        return result;        
    }
}
