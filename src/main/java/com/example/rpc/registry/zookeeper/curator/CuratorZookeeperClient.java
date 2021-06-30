package com.example.rpc.registry.zookeeper.curator;

import com.example.rpc.constants.StateListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import java.nio.charset.StandardCharsets;

/**
 * 基于Curator实现的zk客户端
 * @author Don
 * @date 2021/6/30.
 */
public class CuratorZookeeperClient {
    private final CuratorFramework client;

    public CuratorZookeeperClient(String zkAddress){
        try {
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                    .connectString(zkAddress)
                    .retryPolicy(new RetryNTimes(1, 1000))
                    .connectionTimeoutMs(5000);
            client = builder.build();
            client.getConnectionStateListenable().addListener((client, state) -> {
                if (state == ConnectionState.LOST) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.DISCONNECTED);
                } else if (state == ConnectionState.CONNECTED) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.CONNECTED);
                } else if (state == ConnectionState.RECONNECTED) {
                    CuratorZookeeperClient.this.stateChanged(StateListener.RECONNECTED);
                }
            });
            client.start();
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage(), e);
        }

    }

    public void create(String path, boolean ephemeral) {
        if (!ephemeral) {
            if (checkExists(path)) {
                return;
            }
        }
        int i = path.lastIndexOf('/');
        if (i > 0) {
            create(path.substring(0, i), false);
        }
        if (ephemeral) {
            createEphemeral(path);
        } else {
            createPersistent(path);
        }
    }

    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void doClose() {
        client.close();
    }

    private boolean checkExists(String path) {
        try {
            if (client.checkExists().forPath(path) != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void createEphemeral(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void createEphemeral(String path, String data) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path, dataBytes);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void createPersistent(String path, String data) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            client.create().forPath(path, dataBytes);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void createPersistent(String path) {
        try {
            client.create().forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void stateChanged(int state) {
        System.out.println("zk stated changed, current state:" +state);
    }
}
