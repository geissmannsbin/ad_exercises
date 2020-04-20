package ch.hslu.sw09;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

    private static final int CAPACITY = 1_000_000;

    private Queue<String> queue;

    @Before
    public void initQueue() {
        queue = new ArrayBlockingQueue<>(CAPACITY);
    }

    @Test
    public void testMultipleProducersAndConsumers(){
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final int producerCount = 10;
        final int consumersCount = 10;
        int p = producerCount;
        int c = consumersCount;

        // mixing producers and consumers
        while (p > 0 || c > 0){
            if (p > 0) {
                final int producerNo = producerCount - p;
                int toProduce = CAPACITY / producerCount;
                executor.submit(createProducer(toProduce, producerNo));
                p--;
            }
            if ( c > 0 ){
                int toConsume = CAPACITY / consumersCount;
                executor.submit(createConsumer(toConsume));
                c--;
            }
        }

        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }

    private Runnable createConsumer(int toConsume) {
        return () -> {
            int n = toConsume;
            while (n > 0) {
                String str = queue.poll();
                if (str != null) {
                    Assert.assertNotNull(str);
                    n--;
                }
            }
        };
    }

    private Runnable createProducer(int toProduce, int producerNo) {
        return () -> {
            for (int n = 0; n < toProduce; n++){
                queue.add("Produced by;" + producerNo + ", SN; " + n);
            }
        };
    }

}
