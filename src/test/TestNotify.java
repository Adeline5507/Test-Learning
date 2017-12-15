package test;


public class TestNotify {

	public static void main(String[] args) throws InterruptedException {
		testNotify();
	}
	
	private static final Object obj = new Object();
	public static void testNotify() throws InterruptedException {
		Thread[] rs = new Thread[10];
        for(int i = 0;i < 10;i++) {
            rs[i] = new Thread(new R(i));
        }
        for(Thread r : rs) {
            r.start();
            Thread.sleep(500);
        }
 
        System.out.println("====================");
        Thread.sleep(2000);
        synchronized(obj) {
            obj.notifyAll();
            System.out.println("notifyAll");
        }
        System.out.println("====================");
    }
	static class R implements Runnable {
        int i;
 
        R(int i) {
            this.i = i;
        }
 
        public void run() {
            try {
                synchronized(obj) {
                    System.out.println("线程->  " + i + " 等待中");
                    obj.wait();
                    Thread.sleep(500);
                    System.out.println("线程->  " + i + " 在运行了");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
	
}
