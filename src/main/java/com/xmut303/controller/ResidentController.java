package com.xmut303.controller;

import com.xmut303.entity.PageResult;
import com.xmut303.pojo.*;
import com.xmut303.service.PersonService;
import com.xmut303.service.ResidentService;
import com.xmut303.entity.Result;
import com.xmut303.utils.FaceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;
    @Autowired
    private PersonService personService;

    @RequestMapping("/searchResident")
    public ModelAndView searchBorrowed(Person person, Integer pageNum, Integer pageSize, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();

        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }

        //调用service
        PageResult pageResult = residentService.searchResident(person,pageNum,pageSize);

        //页面
        modelAndView.setViewName("resident_manage");

        //数据
        //搜索框数据回显
        modelAndView.addObject("search",person);
        //分页数据信息
        modelAndView.addObject("pageResult",pageResult);
        //当前页码数
        modelAndView.addObject("pageNum",pageNum);
        //分页请求再次发起的地址
        modelAndView.addObject("gourl",request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping("/addResident")
    @ResponseBody
    public Result addResident(Person person){
        Integer num = 0;
        System.out.println(person.getPhoto_url());
        System.out.println("11111");
        System.out.println(person);
        num += residentService.addResident(person);
        num += residentService.addCarry(person);
//        num += residentService.addEmployment(person);
//        num += residentService.addHealth(person);

        if (num > 1){
            //成功
            return new Result(true,"添加居民成功");
        }else {
            return new Result(false,"添加居民失败");
        }
    }

    //检测头像可用性
    @PostMapping("/fileload")
    public Result fileUpload(@RequestParam("photo_url") MultipartFile portrait, HttpServletRequest request) throws Exception{

        //暂存头像文件，用来对比头像库中的预警人员头像信息
        String temp = "src/main/webapp/temp/";

        //判断files为空，如果为空，上传失败，否则才开始进行上传的操作
        if (portrait!=null){
            String tempUrl = temp + portrait.getOriginalFilename();

            //把上传的待检测头像暂存到temp文件夹
            portrait.transferTo(new File(tempUrl));

            FaceInfo faceInfo = new FaceInfo();
            System.out.println(faceInfo.Sample(tempUrl));
            BigDecimal Confidentce = faceInfo.Sample(tempUrl).getConfidence();

            //上传的头像检测完后删除
            File file = new File(tempUrl);
            Boolean flag = file.delete();
            System.out.println(flag);

            if (Confidentce.doubleValue() > 50){
                System.out.println("预警人员");
                return new Result(null,"预警人员");
            }else{

                return new Result(null, "头像可用");
            }

        }
        return new Result(null,"上传失败");
    }

    @RequestMapping("/findByIDCard")
    @ResponseBody
    public Result<Person> findByIDCard(String IDCard){
        System.out.println(IDCard);
        Person person = personService.findByIDCard(IDCard);
        System.out.println(person);
        return new Result<>(true,"",person);
    }

    //检测头像可用性
    @PostMapping("/fileUp")
    public Result fileUp(@RequestParam("photo_url") MultipartFile portrait, HttpServletRequest request) throws Exception{

        //获取保存文件信息的所在文件的根目录的绝对路径
        String path = "src/main/webapp/img/";

        System.out.println("111");
        //判断files为空，如果为空，上传失败，否则才开始进行上传的操作
        if (portrait!=null){
            System.out.println("头像为空");
            String Url = path + portrait.getOriginalFilename();
            portrait.transferTo(new File(Url));
            return new Result(null,null);


        }
        return new Result(null,"头像为空");
    }

    @RequestMapping("/editResident")
    @ResponseBody
    public Result editResident(Person person){
        Integer num = residentService.editResident(person);
        System.out.println("num:" +num);

        if (num > 0){
            return new Result(true,"修改成功！");
        }else {
            return new Result(false,"修改失败！");
        }
    }

    @RequestMapping("/deleteResident")
    @ResponseBody
    public Result deleteResident(String IDCard){
        System.out.println(IDCard);
        String photo_url = personService.findByIDCard(IDCard).getPhoto_url();
        Integer num = residentService.deleteResident(IDCard);
        if (photo_url != null){
            String path = "src/main/webapp/img/" + photo_url;
            System.out.println(path);
            File file = new File(path);
            Boolean flag = file.delete();
        }


        if (num > 0){
            return new Result(true,"删除成功！");
        }else {
            return new Result(false,"删除失败！");
        }
    }

}


