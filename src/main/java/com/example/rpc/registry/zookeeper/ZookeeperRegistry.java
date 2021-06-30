package com.example.rpc.registry.zookeeper;

import com.example.rpc.registry.RegistryService;
import com.example.rpc.registry.zookeeper.curator.CuratorZookeeperClient;

/**
 * zookeeper注册中心
 * @author Don
 * @date 2021/6/30.
 */
public class ZookeeperRegistry implements RegistryService {

    //TODO：不做扩展，先写死
    private CuratorZookeeperClient curatorZookeeperClient;

    public ZookeeperRegistry(String address){
        curatorZookeeperClient = new CuratorZookeeperClient(address);
    }

    @Override
    public void register(String path) {
        curatorZookeeperClient.create(path, true);
    }

    @Override
    public void unregister(String path) {
        curatorZookeeperClient.delete(path);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
