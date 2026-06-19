package com.mariluz.notifications;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "RESEND_API_KEY=re_123456789987654321" 
})
class NotificationsApplicationTests {

    @Test
    void contextLoads() {
    }
}