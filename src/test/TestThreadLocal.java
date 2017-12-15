package test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadLocal implements Runnable {

    static class PropertyHolder {
        static ThreadLocal<Long> id = new ThreadLocal<Long>();
        static ThreadLocal<String> name = new ThreadLocal<String>();

        public static void setProperties() {
            id.set(Thread.currentThread().getId());
            name.set(Thread.currentThread().getName());
        }

        public static void printProperties() {
            System.out.println("current thread id is:" + id.get() + " current thread name is:" + name.get());
        }
        
        public static Long getLong(){
            return id.get();
        }
    }

    @Override
    public void run() {
        PropertyHolder.setProperties();
        PropertyHolder.printProperties();
        System.out.println(PropertyHolder.getLong().equals(Thread.currentThread().getId())?"true":"false");
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            tp.execute(new TestThreadLocal());
        }
    }

}
