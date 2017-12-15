package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSignal {

	public static void main(String[] args) throws InterruptedException {
		testSignal();
	}
	
	private static final  Lock lock = new ReentrantLock();  
    private static final  Condition condition = lock.newCondition(); 
    private static volatile int sig = -1;
	public static void testSignal() throws InterruptedException {
		Thread[] rs = new Thread[10];
        for(int i = 0;i < 10;i++) {
            rs[i] = new Thread(new S(i));
        }
        for(Thread r : rs) {
            r.start();
            Thread.sleep(500);
        }
 
        Thread.sleep(1000);
        lock.lock();
        sig = 9;
    	condition.signalAll();
    	System.out.println("signalAll");
    	lock.unlock();
	}
	static class S implements Runnable {
        int i;
 
        S(int i) {
            this.i = i;
        }
 
        public void run() {
//        	lock.lock();
//        	try {
//				while(true){
//					if(sig==i){
//			            System.out.println("线程->  " + i + " 在运行了");
//			            Thread.sleep(1000);
//			            sig--;
//			            condition.signalAll();
//			            System.out.println(i+"-> signalAll");
//			            break;
//					}else{
//						System.out.println("线程->  " + i + " 等待中");
//						Thread.sleep(500);
//						condition.await();
//					}
//				}
//            } catch (Exception e) {
//				e.printStackTrace();
//			}finally{
//				lock.unlock();
//			}
        	
        	
        	lock.lock();
            try {
                while(true){
                  
                        System.out.println("线程->  " + i + " 等待中");
                        Thread.sleep(500);
                        condition.await();
                        System.out.println("线程->  " + i + " 在运行了");
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        	
        	
        }
    }
}
