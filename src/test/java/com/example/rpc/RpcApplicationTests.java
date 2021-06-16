package com.example.rpc;

import com.example.rpc.consumer.CountryService;
import com.example.rpc.provider.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpcApplicationTests {

//    @Autowired
//    CityServiceImpl cityService;

    @Autowired
    CountryService countryService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRegisty(){
        String name = countryService.getCityName();
    }
}
