package com.moose.community;

import com.moose.community.dao.DiscussPostMapper;
import com.moose.community.dao.LoginTicketMapper;
import com.moose.community.dao.UserMapper;
import com.moose.community.entity.DiscussPost;
import com.moose.community.entity.LoginTicket;
import com.moose.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class Mappertest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testselect(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertId(){
        User user = new User();
        user.setUsername("moose");
        user.setPassword("123456");
        user.setEmail("moose.@123.com");
        user.setSalt("abc");
        user.setHeaderUrl("http://www.newcoder.com/101.png");
        user.setCreateTime(new Date());

        int row = userMapper.insertUser(user);
        System.out.println(row);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser(){
        int row = userMapper.updateStatus(150, 1);
        System.out.println(row);

        int row1 = userMapper.updateHeader(150, "http://newcoder.com/105.png");
        System.out.println(row1);
    }

    @Test
    public void testselectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 1, 10);
        for (DiscussPost discussPost:list){
            System.out.println(discussPost);
        }
        int row = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(row);
    }

    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLobinTicket(){
        //先查
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc",1);

        //再查一次
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }


}
