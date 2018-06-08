package com.cic.dao;

import com.cic.service.SysBooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysBooksMapperTest {

    @Autowired
    private SysBooksService sysBooksService;

    @Test
    public void getMyBorrowList() {
        List a = sysBooksService.getMyBorrowList("6e2ae48df3f24df79829");
        System.out.println(a.size());
    }
}