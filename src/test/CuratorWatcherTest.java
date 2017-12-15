package test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;

public class CuratorWatcherTest {
    /** Zookeeper info */
    private static final String ZK_ADDRESS = "pcdtccas01d.emea1.cis.trcloud,pcdtccas02d.emea1.cis.trcloud,pcdtccas03d.emea1.cis.trcloud";
    private static final String ZK_PATH = "/com/pointcarbon/loaderframework/stopSignal";

    public static void main(String[] args) throws Exception {
        // 1.Connect to zk
//        CuratorFramework client = CuratorFrameworkFactory.newClient(
//                ZK_ADDRESS,
//                new RetryNTimes(10, 5000)
//        );
        
        CuratorFramework client = CuratorFrameworkFactory

                .builder()

                .connectString("pcdtccas01d.emea1.cis.trcloud,pcdtccas02d.emea1.cis.trcloud,pcdtccas03d.emea1.cis.trcloud")

                .namespace("com/pointcarbon/loaderframework")

                .retryPolicy(new RetryNTimes(2000,20000))

                .build();

        client.start();
         
        System.out.println("zk client start successfully!");

        // 2.Register watcher
        final NodeCache nodeCache = new NodeCache(client, ZK_PATH, false);  
        nodeCache.start(true);  
        nodeCache.getListenable().addListener(  
            new NodeCacheListener() {  
                @Override  
                public void nodeChanged() throws Exception {  
                    System.out.println("Node data is changed, new data: " +   
                        new String(nodeCache.getCurrentData().getData()));  
                }  
            }  
              
        );
        System.out.println("Register zk watcher successfully!");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
