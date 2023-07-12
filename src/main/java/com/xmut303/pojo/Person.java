package com.xmut303.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/11 11:32
 */

@Data
public class Person {
    private String IDCard;      //身份证
    private String name;        //姓名
    private String sex;         //性别
    private String birth;       //出生日期
    private String address;     //厦门居住地址
    private String photo_url;   //头像
    private String warn_type;   //犯罪类型

    private String carriedID;   //携带人id
    private String condition;   //健康状况
    private String job;         //工作信息

    private String email;       //邮件
    private String education;   //教育信息
    private String political;   //政治信息
    private String religion;    //宗教信仰
    private String isMarry;     //婚姻状况

    private Integer age;        //年龄
    private String domicileplace;//户籍地址
    private String tel;         //电话号码
    private String nation;      //民族
}
