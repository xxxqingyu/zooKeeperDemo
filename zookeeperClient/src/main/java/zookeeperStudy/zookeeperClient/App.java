package zookeeperStudy.zookeeperClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.lang.model.element.VariableElement;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String connectionString="192.168.102.128:2181";
	private static int sessionTimeout=999999;
	
    public static void main(String[] args ) throws Exception
    {
    	System.out.println("Main");
        if (args.length<=1) {
			return;
		}
        String method=args[0].toUpperCase();
         
        String[] new_args = Arrays.copyOfRange(args, 1, args.length - 1);
        
        switch (method) {
			case "C":
				CreateGroup(new_args);
				break;
			case "J":
				JoinGroup(new_args);
				break;
			case "L":
				ListGroup(new_args);
				break;
			default:
				break;
		}
    }
    
    public static void Test() throws Exception{
    	Watcher watcher=new Watcher(){
        	public void process(WatchedEvent event){
        		System.out.println("监听到的事件："+ event);
        	}
        };
        
        final ZooKeeper zookeeper =new ZooKeeper(connectionString,sessionTimeout,watcher);
        System.out.println("获得连接："+ zookeeper);
        final byte[] data=zookeeper.getData("/zk", watcher, null);
        System.out.println("读取的值："+ new String(data));
        zookeeper.close();
	}
    
    public static void CreateGroup(String[] args) throws IOException, InterruptedException, KeeperException{
		org.zk.CreateGroup.main(args);
	}
    
    public static void JoinGroup(String[] args) throws IOException, InterruptedException, KeeperException{
		org.zk.JoinGroup.main(args);
	}
    
    public static void ListGroup(String[] args) throws IOException, InterruptedException, KeeperException {
		org.zk.ListGroup.main(args);
	}
}
