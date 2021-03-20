package com.seal.delay;

import com.seal.delay.mq.DelayMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqDelayApplicationTests {
    @Autowired
    private DelayMessageSender sender;

    @Test
    public void contextLoads() {
        sender.sendDelayMsg("111", 1 * 1000);
    }

}
