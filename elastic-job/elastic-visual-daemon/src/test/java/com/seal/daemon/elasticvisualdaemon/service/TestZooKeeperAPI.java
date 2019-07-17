package com.seal.daemon.elasticvisualdaemon.service;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/16 18:58
 * @description
 **/
public class TestZooKeeperAPI {

    //会话超时时间
    private final int SESSION_TIMEOUT = 30 * 1000;

    //ZooKeeper服务地址
    private static final String SERVER = "47.107.152.93:2181";

    //创建zookeeper实例
    private ZooKeeper zooKeeper = null;

    //创建Watcher实例
    private Watcher watcher = new Watcher() {

        @Override
        public void process(WatchedEvent event) {
            System.out.println("WatchedEvent: " + event);
        }
    };

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(SERVER, SESSION_TIMEOUT, watcher);
    }

    /**
     * 测试zookeeper基本操作
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void test() throws KeeperException, InterruptedException {
        //1 创建节点
        String resultPath = zooKeeper.create("/path1", "path1 data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点 " + resultPath + "已经创建");

        //2 获取节点数据
        String resultData = new String(zooKeeper.getData("/path1", false, null));
        System.out.println("节点 /path1 的数据是：" + resultData);

        //3 修改节点数据
        zooKeeper.setData("/path1", "modified data".getBytes(), -1); //-1表示忽略版本

        //查看是否修改成功
        System.out.println("节点 /path1 的数据是：" + new String(zooKeeper.getData("/path1", false, null)));

        //4 创建子节点
        zooKeeper.create("/path1/sub1", "sub1 data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点/path1/sub1 的数据是：" + new String(zooKeeper.getData("/path1/sub1", false, null)));

        zooKeeper.create("/path1/sub2", "sub2 data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点/path1/sub2 的数据是：" + new String(zooKeeper.getData("/path1/sub2", false, null)));

        //5 获取子节点
        List<String> childen = zooKeeper.getChildren("/path1", watcher);
        System.out.println(childen);

        //6 删除子节点
        zooKeeper.delete("/path1/sub2", -1); //-1表示忽略版本
        zooKeeper.delete("/path1/sub1", -1);

        //7 判断节点是否存在
        System.out.println("节点/path1/sub1是否存在：" + zooKeeper.exists("/path1/sub1", watcher));
        System.out.println("节点/path1是否存在：" + zooKeeper.exists("/path1", watcher));

        //8 删除 /path1 节点
        zooKeeper.delete("/path1", -1);

        //9 关闭连接
        zooKeeper.close();
    }


}




