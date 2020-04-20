package ch.hslu.sw08;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BoundedBufferTest {
    static final int BUFFER_CAPACITY = 1024;

    @Test
    public void testSync() throws InterruptedException {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        Assert.assertTrue(buf.empty());

        // fill up buffer
        for (int n = 0; n < BUFFER_CAPACITY; n++) {
            buf.put("String" + n);
        }

        Assert.assertEquals(BUFFER_CAPACITY, buf.size());
        Assert.assertTrue(buf.full());
        Assert.assertFalse(buf.empty());

        // empty buffer
        for (int n = 0; n < BUFFER_CAPACITY; n++) {
            Assert.assertEquals("String" + n, buf.get());
        }
        Assert.assertTrue(buf.empty());
    }

    @Test
    public void testProducerConsumer() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);

        Thread producer = new Thread(() -> {
            for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                String str = "String" + n;
                try {
                    buf.put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                String str = "String" + n;
                try {
                    Assert.assertEquals(str, buf.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testProducerTwoConsumers() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        Thread producer = new Thread(() -> {
            for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY; n++) {
                String str = "String" + n;
                try {
                    buf.put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Runnable consumerRunnable = () -> {
            for (int n = 0; n < BoundedBufferTest.BUFFER_CAPACITY / 2; n++) {
                try {
                    Assert.assertNotNull(buf.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread consumer1 = new Thread(consumerRunnable);
        Thread consumer2 = new Thread(consumerRunnable);
        producer.start();
        consumer1.start();
        consumer2.start();
        try {
            producer.join();
            consumer1.join();
            consumer2.join();
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testNProducersNConsumers() {
        BoundedBuffer<String> buf = new BoundedBuffer<>(BUFFER_CAPACITY);
        final int nProducers = 10;
        final int nConsumers = 10;
        List<Thread> producers = new ArrayList<>(nProducers);
        List<Thread> consumers = new ArrayList<>(nConsumers);
        Runnable producerRunnable = () -> {
            for (int n = 0; n < BUFFER_CAPACITY / nProducers; n++) {
                try {
                    buf.put("String" + n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable consumerRunnable = () -> {
            for (int n = 0; n < BUFFER_CAPACITY / nConsumers; n++) {
                try {
                    Assert.assertNotEquals(0, buf.get().length());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int n = 0; n < nProducers; n++) {
            producers.add(new Thread(producerRunnable));
        }
        for (int n = 0; n < nConsumers; n++) {
            consumers.add(new Thread(consumerRunnable));
        }
        for (Thread c : consumers) {
            c.start();
        }
        for (Thread p : producers) {
            p.start();
        }
        try {
            for (Thread p : producers) {
                p.join();
            }
            for (Thread c : consumers) {
                c.join();
            }
            Assert.assertTrue(buf.empty());
        } catch (InterruptedException iEx) {
            System.err.println(iEx.getMessage());
        }
    }
}
