package com.xmut303.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmut303.dao.ResidentMapper;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Person;
import com.xmut303.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public PageResult searchResident(Person person, Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum,pageSize);

        Page<Person> page;

        page = residentMapper.selectResident(person);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public int addResident(Person person) {
        person.setPhoto_url(person.getPhoto_url().substring(person.getPhoto_url().lastIndexOf("\\")+1));
        System.out.println(person.getPhoto_url());

        return residentMapper.addResident(person);
    }

    @Override
    public int addCarry(Person person) {
        return residentMapper.addCarry(person);
    }

    @Override
    public int addEmployment(Person person) {
        return residentMapper.addEmployment(person);
    }

    @Override
    public int addHealth(Person person) {
        return residentMapper.addHealth(person);
    }

    @Override
    public int editResident(Person person) {
        return residentMapper.editResident(person);
    }

    @Override
    public int deleteResident(String IDCard) {
        return residentMapper.deleteResident(IDCard);
    }
}
