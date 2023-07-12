package com.xmut303.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.xml.bind.v2.model.core.ID;
import com.xmut303.dao.WarningMapper;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Person;
import com.xmut303.service.PersonService;
import com.xmut303.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/5 15:16
 */
@Service
public class WarningServiceImpl implements WarningService {

    @Autowired
    private WarningMapper warningMapper;

    @Override
    public PageResult findWarningPeople(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Person> people = warningMapper.findWarningPeople();
        return new PageResult(people.getTotal(),people.getResult());
    }

    @Override
    public Person findWarningPeopleByID(String IDCard) {
        return warningMapper.findWarningPeopleByID(IDCard);
    }

    @Override
    public Integer editWarning(Person person) {
        return warningMapper.editWarning(person);
    }

    @Override
    public Integer delWarning(String IDCard) {
        return warningMapper.delWarning(IDCard);
    }

    @Override
    public PageResult searchWarning(Person person,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Person> people = warningMapper.searchWarning(person);
        return new PageResult(people.getTotal(),people.getResult());
    }
}
