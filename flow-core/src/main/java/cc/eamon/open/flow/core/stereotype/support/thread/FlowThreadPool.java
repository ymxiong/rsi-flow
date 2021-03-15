package cc.eamon.open.flow.core.stereotype.support.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lucas
 * Email: lucas@eamon.cc
 * Date: 2020/7/5
 */
public class FlowThreadPool {

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(10);
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30,
            TimeUnit.MINUTES, sPoolWorkQueue, new CustomThreadFactory(), new CustomRejectedExecutionHandler());
    
    public static void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //记录异常
            System.out.println("error...................");
        }
    }


    private static class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = FlowThreadPool.class.getSimpleName() + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }

}
