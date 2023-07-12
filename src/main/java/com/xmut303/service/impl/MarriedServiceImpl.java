package com.xmut303.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmut303.dao.MarriedMapper;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Married;
import com.xmut303.service.MarriedService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/9 11:48
 */
@Service
public class MarriedServiceImpl implements MarriedService {

    @Autowired
    private MarriedMapper marriedMapper;

    @Override
    public PageResult searchMarried(Married married, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Married> marriedPage = marriedMapper.searchMarried(married);
        return new PageResult(marriedPage.getTotal(),marriedPage.getResult());
    }

    @Override
    public PageResult searchAllMarried(Married married, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Married> marriedPage = marriedMapper.searchAllMarried();
        return new PageResult(marriedPage.getTotal(),marriedPage.getResult());
    }

    @Override
    public Married findMarriedByID(Integer ID) {
        return marriedMapper.findMarriedByID(ID);
    }

    @Override
    public Integer addMarried(Married married) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String marriedDate = simpleDateFormat.format(new Date());
        married.setMarriedDate(marriedDate);
        System.out.println("impl"+married);
        return marriedMapper.addMarried(married);
    }

    @Override
    public Married isMarried(String IDCard) {
        return marriedMapper.isMarried(IDCard);
    }

    @Override
    public Integer updateMarried(Married married) {
        return marriedMapper.updateMarried(married);
    }

    @Override
    public Married SelectMarByKid(String kid) {
        return marriedMapper.SelectMarByKid(kid);
    }
}
