package com.xmut303.service;

import com.github.pagehelper.Page;
import com.xmut303.entity.PageResult;
import com.xmut303.pojo.Married;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/9 11:46
 */
public interface MarriedService {

    PageResult searchMarried(Married married, Integer pageNum, Integer pageSize);

    PageResult searchAllMarried(Married married, Integer pageNum, Integer pageSize);

    Married findMarriedByID(Integer ID);

    Integer addMarried(Married married);

    Married isMarried(String IDCard);

    Integer updateMarried(Married married);

    Married SelectMarByKid(String kid);
}
