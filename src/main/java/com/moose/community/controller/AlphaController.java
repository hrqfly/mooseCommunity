package com.moose.community.controller;

import com.moose.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/giao")
    @ResponseBody
    public String saygiao(){
        return "bro giao.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String findDate(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){

        //获取请求
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+"--"+value);
        }
        System.out.println(request.getParameter("code"));
        //返回一个响应网页
        response.setContentType("text/html;charset = utf-8");

        try (PrintWriter writer = response.getWriter()){
            writer.write("<h1>newbeer</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Get请求

    //student?current = 1 & limit  = 20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudengts(
            @RequestParam (name = "current" ,required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false ,defaultValue = "10") int limit){
        return limit+" students in one page now get "+current;
    }


    //Post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String savaStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应动态的html
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","moose");
        mav.addObject("age","23");
        mav.setViewName("/demo/view");
        return mav;
    }
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","moose");
        model.addAttribute("age","81");
        return "/demo/view";
    }

    //响应json数据
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",20);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",20);
        emp.put("salary",8000.00);
        list.add(emp);
        Map<String,Object> emp1 = new HashMap<>();
        emp1.put("name","李四");
        emp1.put("age",22);
        emp1.put("salary",9000.00);
        list.add(emp1);
        return list;
    }

}
