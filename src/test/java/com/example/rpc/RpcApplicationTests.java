package com.example.rpc;

import com.example.rpc.annotation.EnableRpc;
import com.example.rpc.provider.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpcApplicationTests {

    @Autowired
    CityServiceImpl cityService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRegisty(){
        String name = cityService.getName();
    }
}
