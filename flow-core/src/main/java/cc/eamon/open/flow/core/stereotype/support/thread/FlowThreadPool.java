package cc.eamon.open.flow.core.stereotype.support.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lucas
 * @date: 2020/7/5
 * @email: lucas@eamon.cc
 */
public class FlowThreadPool {

    private static final int CORE_POOL_SIZE=10;
    private static final int MAXIMUM_POOL_SIZE=30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue=new LinkedBlockingQueue<>(10);
    public static ThreadPoolExecutor pool= new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,30,
            TimeUnit.MINUTES,sPoolWorkQueue,new CustomThreadFactory(), new CustomRejectedExecutionHandler());

    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 							即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 						          任务会交给RejectedExecutionHandler来处理
     */

//    public static void init() {
//
//        pool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,30,
//                TimeUnit.MINUTES,sPoolWorkQueue,new CustomThreadFactory(), new CustomRejectedExecutionHandler());
//    }

    public static void destory() {
        if(pool !=null) {
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


    private static  class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName =  FlowThreadPool.class.getSimpleName()+count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }

}
