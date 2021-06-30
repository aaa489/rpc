package com.example.rpc;

import com.example.rpc.registry.RegistryService;
import com.example.rpc.registry.zookeeper.ZookeeperRegistry;
import com.example.rpc.registry.zookeeper.curator.CuratorZookeeperClient;
import org.junit.jupiter.api.Test;

/**
 * @author Don
 * @date 2021/6/30.
 */
public class ZkTest {

    @Test
    public void test(){
        RegistryService registryService = new ZookeeperRegistry("192.168.10.118:2181");
        registryService.register("/don/rpc/provider/cityService");
    }
}
