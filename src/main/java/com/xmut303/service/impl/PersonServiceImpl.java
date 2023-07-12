package com.xmut303.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmut303.dao.PersonMapper;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Person;
import com.xmut303.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:58
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PageResult findAllPerson(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Person> people = personMapper.findAllPerson();
        return new PageResult(people.getTotal(),people.getResult());
    }

    @Override
    public Person findByIDCard(String IDCard) {
        return personMapper.findByIDCard(IDCard);

    }

    @Override
    public List<Person> findByMarried() {
        return personMapper.findByMarried();
    }

    @Override
    public PageResult findAllResident(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Person> people = personMapper.findAllResident();
        return new PageResult(people.getTotal(),people.getResult());
    }
}
