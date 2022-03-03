package com.moose.community.controller.interceptor;

import com.moose.community.entity.LoginTicket;
import com.moose.community.entity.User;
import com.moose.community.service.UserService;
import com.moose.community.util.CookieUtil;
import com.moose.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    //注入持有用户信息的工具类
    @Autowired
    private HostHolder hostHolder;

    //通过cookie获取凭证并放入hostholder
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取凭证
        String ticket = CookieUtil.getValue(request,"ticket");

        if (ticket!=null){
            //查询凭证
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            //检查凭证是否有效
            if(loginTicket!=null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())){
                //凭证有效，则根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUserId());
                //在本次请求中持有该用户
                hostHolder.setUsers(user);
            }
        }

        return true;
    }

    //hostholder里的用户加入模板以供模板使用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user!=null && modelAndView!=null){
            modelAndView.addObject("loginUser",user);
        }
    }

    //模板使用结束后释放掉hostholder里的用户
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
