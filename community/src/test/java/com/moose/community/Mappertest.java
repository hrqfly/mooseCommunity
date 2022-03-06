package com.moose.community;

import com.moose.community.dao.DiscussPostMapper;
import com.moose.community.dao.LoginTicketMapper;
import com.moose.community.dao.MessageMapper;
import com.moose.community.dao.UserMapper;
import com.moose.community.entity.DiscussPost;
import com.moose.community.entity.LoginTicket;
import com.moose.community.entity.Message;
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

    @Autowired
    private MessageMapper messageMapper;

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


    @Test
    public void testSelectLetters(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);

        for (Message m :messages){
            System.out.println(m);
        }
        int i = messageMapper.selectConversationCount(111);
        System.out.println(i);

        List<Message> messages1 = messageMapper.selectLetters("111_112", 0, 10);
        for (Message m1 :messages1){
            System.out.println(m1);
        }

        int i1 = messageMapper.selectLetterCount("111_112");
        System.out.println(i1);

        int i2 = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(i2);


    }


}
