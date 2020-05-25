import org.apache.zookeeper.*;

import java.io.IOException;

public class ZKTest {
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ZooKeeper zk = new ZooKeeper("10.159.218.36:2181", 3000000, null);
        System.out.println("=========创建节点===========");
        if(zk.exists("/halin", false) == null)
        {
            zk.create("/halin", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("=============查看节点是否安装成功===============");
        System.out.println(new String(zk.getData("/halin", false, null)));

        System.out.println("=========修改节点的数据==========");
        zk.setData("/halin", "zNode2".getBytes(), -1);
        System.out.println("========查看修改的节点是否成功=========");
        System.out.println(new String(zk.getData("/test", false, null)));

        System.out.println("=======删除节点==========");
        zk.delete("/test", -1);
        System.out.println("==========查看节点是否被删除============");
        System.out.println("节点状态：" + zk.exists("/test", false));
        zk.close();
    }
}
