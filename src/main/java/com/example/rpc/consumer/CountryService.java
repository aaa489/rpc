package com.example.rpc.consumer;

import com.example.rpc.annotation.Reference;
import com.example.rpc.provider.api.CityService;
import org.springframework.stereotype.Service;

/**
 * @author Don
 * @date 2021/6/15.
 */
@Service
public class CountryService {

    @Reference
    private CityService cityService;

    public String getCityName(){
        return cityService.getName();
    }
}
