package com.example.rpc.registry;

/**
 * 注册中心服务接口
 * @author Don
 * @date 2021/6/30.
 */
public interface RegistryService {
     /**
       * 注册
       **/
    void register(String path);
    /**
     * 注销
     **/
    void unregister(String path);
    /**
     * 订阅
     **/
    void subscribe();
    /**
     * 取消订阅
     **/
    void unsubscribe();
}
