package com.xmut303.dao;

import com.xmut303.pojo.Admin;
import org.apache.ibatis.annotations.Select;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:16
 */
public interface AdminMapper {

    @Select("select * from admin where username=#{username} and password=#{password}")
    Admin findAdmin(Admin admin);
}
