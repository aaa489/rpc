package com.example.rpc.rpc;

import com.example.rpc.provider.CityServiceImpl;

/**
 * @author Don
 * @date 2021/6/16.
 */
public class MyInvoker implements Invoker{

    @Override
    public Result invoke() {
        //TODO:远程调用、序列化、负载均衡、集群容错
        //模拟调用成功
        CityServiceImpl cityService = new CityServiceImpl();
        return Result.success(cityService.getName());
    }
}
