package com.example.rpc.provider;

import com.example.rpc.annotation.Service;
import com.example.rpc.provider.api.CityService;

/**
 * @author Don
 * @date 2021/6/11.
 */
@Service
public class CityServiceImpl implements CityService {
    @Override
    public String getName() {
        return "hangzhou";
    }
}
