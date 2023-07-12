package com.xmut303.service;

import com.github.pagehelper.Page;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Person;

import java.util.List;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:58
 */
public interface PersonService {

    PageResult findAllPerson(int pageNum,int pageSize);

    Person findByIDCard(String IDCard);

    List<Person> findByMarried();

    PageResult findAllResident(int pageNum,int pageSize);
}
