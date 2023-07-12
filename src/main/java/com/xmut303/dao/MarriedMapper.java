package com.xmut303.dao;

import com.github.pagehelper.Page;
import com.xmut303.pojo.Married;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.support.ManagedArray;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/9 11:39
 */
public interface MarriedMapper {

    /**
     * 用于结婚登记信息对于名字和身份证号的模糊查询
     * @param married
     * @return
     */
    @Select("<script>\n" +
            "    select m.*,p.name from Person p,married m\n" +
            "        <where>\n" +
            "            <if test=\"PID != null and PID.trim() != ''\">\n" +
            "                and (PID like concat('%',#{PID},'%')\n" +
            "                  or spouse like concat('%',#{PID},'%'))\n" +
            "            </if>\n" +
            "           <if test=\"ownName != null and ownName.trim() != ''\">\n"+
            "               and PID in (\n"+
            "               select IDCard\n"+
            "               from person\n"+
            "               where name like concat('%',#{ownName},'%')\n"+
            "               )"+
            "           </if>\n"+
            "           AND m.PID = p.IDCard"+
            "        </where>\n" +
            "</script>")
    @Results(value = {
            @Result(property = "ownName",column = "name")
    })
    Page<Married> searchMarried(Married married);

    /**
     * 查询结婚登记信息
     * @return
     */
    @Select("select m.*,p.name as ownName from Person p,married m\n" +
            "where IDCard in (select PID from married) and IDCard = m.PID")
    Page<Married> searchAllMarried();

    @Select("select * from married where ID = #{ID}")
    Married findMarriedByID(Integer ID);

    @Insert("insert into married (PID,spouse,marriedDate) values (#{PID},#{spouse},#{marriedDate})")
    Integer addMarried(Married married);

    @Select("select * from married where PID=#{IDCard} or spouse=#{IDCard}")
    Married isMarried(String IDCard);


    @Update("<script>\n" +
            "        update married\n" +
            "        <set>\n" +
            "            <if test=\"kid1 != null and kid1.trim() != ''\">\n" +
            "                kid1 = #{kid1},\n" +
            "            </if>\n" +
            "            <if test=\"kid2 != null and kid2.trim() != ''\">\n" +
            "                kid2 = #{kid2},\n" +
            "            </if>\n" +
            "            <if test=\"kid3 != null and kid3.trim() != ''\">\n" +
            "                kid3 = #{kid3},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where ID=#{ID}\n" +
            "    </script>")
    Integer updateMarried(Married married);

    @Select("select * from married where (kid1=#{kid} or kid2 = #{kid} or kid3 = #{kid})")
    Married SelectMarByKid(String kid);

}
