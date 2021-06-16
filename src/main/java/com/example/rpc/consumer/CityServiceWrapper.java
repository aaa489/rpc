package com.example.rpc.consumer;

import com.example.rpc.provider.api.CityService;
import com.example.rpc.rpc.MyInvoker;

/**
 * 只为模拟本地化代理调用，可以采用字节码生成对应类
 * @author Don
 * @date 2021/6/16.
 */
public class CityServiceWrapper implements CityService {

    private MyInvoker myInvoker;

    public CityServiceWrapper(){
        myInvoker = new MyInvoker();
    }

    @Override
    public String getName() {
        return (String)myInvoker.invoke().getData();
    }
}
