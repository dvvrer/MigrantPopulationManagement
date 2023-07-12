package com.xmut303.dao;

import com.github.pagehelper.Page;
import com.xmut303.pojo.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:46
 */
public interface PersonMapper {

    @Select("select * from person where IDCard not in(select PID from warning) and IDCard in (SELECT PID from married where kid3 is not null)")
    Page<Person> findAllPerson();

    @Select("select * from person where IDCard=#{IDCard}")
    Person findByIDCard(String IDCard);


    @Select("select * from person\n" +
            "where IDCard in (select PID from married) \n" +
            "or IDCard in (select spouse from married)")
    List<Person> findByMarried();

    @Select("select * from person where IDCard not in(select PID from warning) and isDelete = '1'")
    Page<Person> findAllResident();
}
