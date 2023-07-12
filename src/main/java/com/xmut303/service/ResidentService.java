package com.xmut303.service;

import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Carry;
import com.xmut303.pojo.Employment;
import com.xmut303.pojo.Health;
import com.xmut303.pojo.Person;


public interface ResidentService {
    public PageResult searchResident(Person person, Integer pageNum, Integer pageSize);

    //新增居民信息
    public int addResident(Person person);
    public int addCarry(Person person);
    public int addEmployment(Person person);
    public int addHealth(Person person);
    public int editResident(Person person);
    public int deleteResident(String  IDCard);
}
