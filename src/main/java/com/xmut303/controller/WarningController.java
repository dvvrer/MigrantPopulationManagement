package com.xmut303.controller;

import com.sun.xml.bind.v2.model.core.ID;
import com.xmut303.entity.PageResult;
import com.xmut303.entity.Result;
import com.xmut303.pojo.Person;
import com.xmut303.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/5/5 15:19
 */

@Controller
@RequestMapping("/warning")
public class WarningController {

    @Autowired
    private WarningService warningService;

    @RequestMapping("/findWarningPeople")
    @ResponseBody
    public ModelAndView findWarningPeople(Person person,Integer pageNum, Integer pageSize, HttpServletRequest request){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageResult pageResult = warningService.findWarningPeople(pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("warning_modal");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("warning_person",person);
        modelAndView.addObject("gourl",request.getRequestURL());
        modelAndView.addObject("pageNum",pageNum);
        return modelAndView;
    }

    @RequestMapping("/findWarningPeopleByID")
    @ResponseBody
    public Result<Person> findWarningPeopleByID(String IDCard){
        Person person = warningService.findWarningPeopleByID(IDCard);
        return new Result<Person>(true,"",person);
    }

    @RequestMapping("/searchWarning")
    @ResponseBody
    public ModelAndView searchWarning(Person person,Integer pageNum, Integer pageSize, HttpServletRequest request){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageResult pageResult = warningService.searchWarning(person,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("warning_modal");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("warning",person);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURL());
        return modelAndView;
    }

    @RequestMapping("/editWarning")
    @ResponseBody
    public Result editWarning(Person person){
        System.out.println("Con:"+person);
        int result = warningService.editWarning(person);
        if (result > 0){
            return new Result(true,"预警人员信息修改成功");
        }else {
            return new Result(false,"预警人员信息修改失败");
        }
    }

    @RequestMapping("/delWarning")
    @ResponseBody
    public Result delWarning(String IDCard){
        int result = warningService.delWarning(IDCard);
        if (result > 0){
            return new Result(true,"预警人员移除成功");
        }else {
            return new Result(false,"预警人员移除失败");
        }
    }
}
