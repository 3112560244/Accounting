package com.qx.accounting;

import com.qx.accounting.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountingApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Util.getMD5("123456"));
    }

}
