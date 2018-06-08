package com.cic.config.dao;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Mybatis & Mapper & PageHelper 配置
 * 
 * @file MybatisConfigurer.java
 * @author daxian
 * @version 2.0.0
 *       Ltd.
 */
@Configuration
public class MybatisConfigurer {
	
	static final String ALIASESPACKAG="com.cic.entity.po";
	static final String MAPPERXMLPATH="classpath:mapper/*.xml";
	static final String BASEPACKAGE="com.cic.dao";
	static final String DATABSAENAME="POSTGRESQL";
	
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        //实体类的包名（根据你的项目自行修改）
        factory.setTypeAliasesPackage(ALIASESPACKAG);

        //配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(properties);

        //添加插件
        factory.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //*.mapper.xml的地址（根据你的项目自行修改）
        factory.setMapperLocations(resolver.getResources(MAPPERXMLPATH));
        
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        //*.mapper(*.dao)的包名（根据你的项目自行修改）
        mapperScannerConfigurer.setBasePackage(BASEPACKAGE);

        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        //tk.mybatis.mapper.common.Mapper
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("notEmpty", "false");//insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        //使用的数据库类型名称（MySQL,Oracle,Postgresql...）
        properties.setProperty("IDENTITY", DATABSAENAME);
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }
}

