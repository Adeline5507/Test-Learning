package test;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

public class CuratorTest2 {

    private CuratorFramework zkTools;

    private ConcurrentSkipListSet watchers = new ConcurrentSkipListSet();

    private static Charset charset = Charset.forName("utf-8");

   

    public static void main(String[] args) throws Exception {

        CuratorTest2 test = new CuratorTest2();

        //test.get();

        //test.register();
        test.put("Hello World");
        int i = 0;
        while(true){
            test.put("Hello World"+i);
            i++;
            Thread.sleep(300000);
        }
        

     }
    
    

    public CuratorTest2() {     

       zkTools = CuratorFrameworkFactory

              .builder()

              .connectString("pcdtccas01d.emea1.cis.trcloud,pcdtccas02d.emea1.cis.trcloud,pcdtccas03d.emea1.cis.trcloud")

              .namespace("com/pointcarbon/loaderframework")

              .retryPolicy(new RetryNTimes(2000,20000))

              .build();

       zkTools.start();

      

     }  

   
 

    public void create() throws Exception{

       zkTools.create()//创建一个路径

       .creatingParentsIfNeeded()//如果指定的节点的父节点不存在，递归创建父节点

       .withMode(CreateMode.PERSISTENT)//存储类型（临时的还是持久的）

       .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)//访问权限

       .forPath("com/pointcarbon/loaderframework");//创建的路径

    }

   

    public void put(String value) throws Exception{

       zkTools.//对路径节点赋值

       setData().

       forPath("com/pointcarbon/loaderframework/stopSignal",value.getBytes(Charset.forName("utf-8")));
       System.out.println("put value:"+value);
    }

   


    public void register() throws Exception{

       String registeNode = "com/pointcarbon/loaderframework/stopSignal";//节点路径

      

       byte[] data = "disable".getBytes(charset);//节点值

 

       CuratorWatcher watcher = new ZKWatch(registeNode);    //创建一个register watcher

      

       Stat stat = zkTools.checkExists().forPath(registeNode);

       if(stat == null){
           System.out.println("Node not exist, need create and registe watcher");

           zkTools.create()

           .creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)

           .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)

           .forPath(registeNode,data);//创建的路径和值
           

       } else {
           System.out.println("Node already exist");
       }
       data = zkTools.getData().usingWatcher(watcher).forPath(registeNode);
       System.out.println("get path form zk : "+registeNode+":"+new String(data,charset));
       

      

       //添加到session过期监控事件中

       //addReconnectionWatcher(registeNode, ZookeeperWatcherType.CREATE_ON_NO_EXITS,watcher);               

      

    }

   

   

   

    public class ZKWatch implements CuratorWatcher{

       private final String path;

      

       public String getPath() {

           return path;

       }

       public ZKWatch(String path) {

           this.path = path;

       }

       @Override

       public void process(WatchedEvent event) throws Exception {

           System.out.println(event.getType());

           if(event.getType() == EventType.NodeDataChanged){

              byte[] data = zkTools.

                     getData().

                     usingWatcher(this).forPath(path);

              System.out.println(path+":"+new String(data,Charset.forName("utf-8")));

           }

       }

      

    }

  

   

    public enum ZookeeperWatcherType{

       GET_DATA,GET_CHILDREN,EXITS,CREATE_ON_NO_EXITS

    }

}