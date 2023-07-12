package com.xmut303.service;

import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Person;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/5 15:15
 */
public interface WarningService {

    PageResult findWarningPeople(int pageNum,int pageSize);

    Person findWarningPeopleByID(String IDCard);

    PageResult searchWarning(Person person,int pageNum,int pageSize);

    Integer editWarning(Person person);

    Integer delWarning(String IDCard);
}
