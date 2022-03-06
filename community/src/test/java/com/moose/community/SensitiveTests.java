package com.moose.community;


import com.moose.community.util.SensitiveFilter;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void sensitivewordTest(){
        String text = "这里可以赌博，这里可以嫖☆娼，这里可以吸☆毒，这里可以开☆票（用于测试）";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
