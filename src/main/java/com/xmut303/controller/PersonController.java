package com.xmut303.controller;

import com.github.pagehelper.PageHelper;
import com.sun.xml.bind.v2.model.core.ID;
import com.xmut303.entity.PageResult;
import com.xmut303.entity.Result;
import com.xmut303.pojo.Person;
import com.xmut303.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 15:03
 */

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/findTopPerson")
    public ModelAndView findAllPerson(){
        int pageNum = 1;
        int pageSize = 10;
        ModelAndView modelAndView = new ModelAndView();
        PageResult pageResult = personService.findAllPerson(pageNum,pageSize);
        modelAndView.setViewName("top_person");
        modelAndView.addObject("pageResult",pageResult);
        return modelAndView;
    }

    @RequestMapping("/findByIDCard")
    @ResponseBody
    public Result<Person> findByIDCard(String IDCard){
        Person person = personService.findByIDCard(IDCard);
        return new Result<>(true,"",person);
    }

    @RequestMapping("/resident_manage")
    public ModelAndView findAllResident(Person person, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){

            pageSize = 10;
        }
        ModelAndView modelAndView = new ModelAndView();
        PageResult pageResult = personService.findAllResident(pageNum,pageSize);
        modelAndView.setViewName("resident_manage");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }


}
