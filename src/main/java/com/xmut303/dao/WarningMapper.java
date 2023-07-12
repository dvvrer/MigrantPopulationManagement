package com.xmut303.dao;

import com.github.pagehelper.Page;
import com.xmut303.pojo.Person;
import org.apache.ibatis.annotations.*;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/5 15:14
 */
public interface WarningMapper {

    /**
     * 查询所有预警人员
     * @return
     */
    @Select("select p.*,w.warn_type from warning w,person p\n" +
            "where w.PID = p.IDCard")
    Page<Person> findWarningPeople();

    /**
     * 通过身份证号查询预警人员
     * @param IDCard
     * @return
     */
    @Select("select p.*,w.warn_type from warning w,person p \n" +
            "where w.PID = p.IDCard and p.IDCard = #{IDCard}")
    Person findWarningPeopleByID(String IDCard);

    /**
     * 通过信息模糊查询预警人员
     * @param person
     * @return
     */
    @Select("<script>select p.*,w.warn_type \n" +
            "        from warning w,person p\n" +
            "        <where>\n" +
            "               w.PID = p.IDCard            "+
            "            <if test=\"name != null and name.trim()!=''\">\n" +
            "                and `name` like concat('%',#{name},'%')\n" +
            "            </if>\n" +
            "            <if test=\"IDCard != null and IDCard.trim()!=''\">\n" +
            "                and IDCard like concat('%',#{IDCard},'%')\n" +
            "            </if>\n" +
            "            <if test=\"sex != null and sex.trim()!=''\">\n" +
            "                and sex like concat('%',#{sex},'%')\n" +
            "            </if>\n" +
            "            <if test=\"birth != null and birth.trim()!=''\">\n" +
            "                and birth like concat('%',#{birth},'%')\n" +
            "            </if>\n" +
            "            <if test=\"address != null and address.trim()!=''\">\n" +
            "                and address like concat('%',#{address},'%')\n" +
            "            </if>\n" +
            "            <if test=\"warn_type != null and warn_type.trim()!=''\">\n" +
            "                and warn_type like concat('%',#{warn_type},'%')\n" +
            "            </if>\n" +
            "        </where></script>")
    Page<Person> searchWarning(Person person);

    /**
     * 修改预警人员（地址，预警类型，性别）
     * @param person
     * @return
     */
    @Update("<script>\n" +
            "        update person p,warning w\n" +
            "        <set>\n" +
            "            <if test=\"sex != null and sex.trim() != ''\">\n" +
            "                sex = #{sex},\n" +
            "            </if>\n" +
            "            <if test=\"address != null and address.trim() != ''\">\n" +
            "                address = #{address},\n" +
            "            </if>\n" +
            "            <if test=\"warn_type != null and warn_type.trim() != ''\">\n" +
            "                w.warn_type = #{warn_type},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where IDCard=#{IDCard} and p.IDCard=w.PID\n" +
            "    </script>")
    Integer editWarning(Person person);

    /**
     * 删除预警人员
     * @param IDCard
     * @return
     */
    @Delete("delete from warning where PID=#{IDCard}")
    Integer delWarning(String IDCard);

}
