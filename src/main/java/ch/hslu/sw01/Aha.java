package ch.hslu.sw01;

import org.apache.logging.log4j.message.Message;

import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Aha {

    private static int execCounter;
    private static final Logger LOG = LogManager.getLogger(Aha.class);

    Aha(final int n) throws InterruptedException {
        execCounter = 0;
        task(n);
        LOG.info("task Executions: " + execCounter);
    }

    private static void task(final int n) throws InterruptedException {
        task1(); task1(); task1(); task1();n
        for (int i = 0; i < n; i++){
            task2(); task2(); task2();
            for (int j = 0; j < n; j++) {
                task3(); task3();
            }
        }
    }

    private static void task1() throws InterruptedException {
        Thread.sleep(5);
        execCounter++;
    }

    private static void task2() throws InterruptedException {
        Thread.sleep(5);
        execCounter++;
    }

    private  static void task3() throws InterruptedException {
        Thread.sleep(5);
        execCounter++;
    }
}
