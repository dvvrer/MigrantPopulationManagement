package com.xmut303.dao;

import com.github.pagehelper.Page;
import com.xmut303.pojo.Person;
import org.apache.ibatis.annotations.*;

public interface ResidentMapper {

    @Select("<script>\n" +
            "            select * from Person\n" +
            "            <where>\n" +
            "                <if test=\"name != null and name.trim() != ''\">\n" +
            "                    and name like concat('%',#{name},'%')\n" +
            "                </if>\n" +
            "                <if test=\"IDCard != null and IDCard.trim() != ''\">\n" +
            "                    and IDCard like concat('%',#{IDCard},'%')\n" +
            "                </if>\n" +
            "            </where>\n" +
            "            order by IDCard\n" +
            "        </script>")
    @Results(id = "ResidentMapper",value = {
            @Result(id = true,property = "IDCard",column = "IDCard"),
            @Result(id = true,property = "name",column = "name"),
            @Result(id = true,property = "sex",column = "sex"),
            @Result(id = true,property = "birth",column = "birth"),
            @Result(id = true,property = "address",column = "address"),

            @Result(id = true,property = "IDCard",column = "IDCard"),
            @Result(id = true,property = "name",column = "name"),
            @Result(id = true,property = "sex",column = "sex"),
            @Result(id = true,property = "birth",column = "birth"),
            @Result(id = true,property = "address",column = "address")
    })
    public Page<Person> selectResident(Person person);

    @Insert("INSERT INTO Person (IDCard,`name`,sex,birth,address,photo_url,tel,nation,email,education,political,isMarry,age,domicileplace,religion) VALUES " +
            "(#{IDCard},#{name},#{sex},#{birth},#{address},#{photo_url},#{tel},#{nation},#{email},#{education},#{political},#{isMarry},#{age},#{domicileplace},#{religion})")
    public Integer addResident(Person person);

    @Insert("INSERT INTO Health (PID,`condition`) VALUES (#{IDCard},#{condition})")
    public int addCarry(Person person);

    @Insert("INSERT INTO employment (PID,job) VALUES (#{IDCard},#{job})")
    public int addEmployment(Person person);

    @Insert("INSERT INTO carry (PID,carriedID) VALUES (#{IDCard},#{carriedID})")
    public int addHealth(Person person);

    @Update("UPDATE Person SET IDCard = #{IDCard},`name` = #{name},sex = #{sex},birth = #{birth},address = #{address},tel = #{tel},email = #{email},education = #{education},political = #{political},isMarry = #{isMarry},age = #{age} WHERE IDCard = #{IDCard}")
    public int editResident(Person person);

//    @Delete("DELETE FROM Person WHERE IDCard = #{IDCard}")
    @Update("UPDATE Person set isDelete = '0' WHERE IDCard = #{IDCard}")
    public int deleteResident(String IDCard);

}
