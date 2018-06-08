package com.cic.config.dao;

import org.apache.ibatis.exceptions.TooManyResultsException;

import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    void save(T model) throws Exception;//持久化
    void save(List<T> models) throws Exception;//批量持久化
    void deleteById(String id) throws Exception;//通过主鍵刪除
    void deleteByIds(String ids) throws Exception;//批量刪除 eg：ids -> “1,2,3,4”
    void update(T model) throws Exception;//更新
    T findById(String id) throws Exception;//通过ID查找
    T findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> findListBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> findByIds(String ids) throws Exception;//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> findByCondition(Condition condition) throws Exception;//根据条件查找
    List<T> findAll() throws Exception;//获取所有
    List<T> findByVo(T t)  throws Exception; //根据条件查询
   // PageInfo<T> selectPage(T parameter) throws Exception; //分页查询
    
}
