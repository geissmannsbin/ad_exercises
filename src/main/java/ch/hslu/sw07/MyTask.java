package ch.hslu.sw07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MyTask implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    @Override
    public void run(){

    }

    public static void main (final String[] args) throws InterruptedException {
        final MyTask myTask = new MyTask();
        final Thread thread = new Thread(myTask, "MyTask-Thread");
        thread.start();
        while (thread.isAlive()) {
            Thread.sleep((10));
        }
        for (int i = 0; i < 1000; i++) {
            LOG.info("x");
        }
    }
}
