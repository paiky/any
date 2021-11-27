package xyz.linp.any.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author linp
 * @date 2021/11/26 23:06
 * @Description
 */
@SpringBootTest
public class vpntest {
    @Autowired
    private MyClientController myClientController;

    @Test
    public void test(){
        myClientController.craw();
    }
}
