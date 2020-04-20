package ch.hslu.sw09.count;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OwnCounterTest {

    private static final Logger LOG = LogManager.getLogger(OwnCounterTest.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /** declare parameter for number of threads and number of Increments and number of Decrements
         * so that these settings are configured in one place. Ideal number of threads is
         * number of precessors plus 1
         */
        int numberOfThreads = Runtime.getRuntime().availableProcessors() + 1;
        int numberOfINcAndDec = 10000;

        final AtomicCounter atomicCounter = new AtomicCounter();
        final SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        ArrayList<Future<Long>> resultsAtomic = new ArrayList<>();
        ArrayList<Future<Long>> resultsSynchronized = new ArrayList<>();

        long timeElapsedSynchronized = 0;
        long timeElapsedAtomic = 0;

        LOG.info("Simulation starting...");

        for ( int i = 0; i < numberOfThreads; i++){
            resultsAtomic.add(service.submit(new CountingTask(numberOfINcAndDec, numberOfINcAndDec, atomicCounter)));
        }

        // get time from all threads accessing the AtomicCounter (blocking)
        for (Future<Long> future : resultsAtomic) {
            timeElapsedAtomic += future.get();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            resultsSynchronized
                    .add(service.submit(new CountingTask(numberOfINcAndDec, numberOfINcAndDec, synchronizedCounter)));
        }

        // get time from all threads accessing the SynchronizedCounter (blocking)
        for (Future<Long> future : resultsSynchronized) {
            timeElapsedSynchronized += future.get();
        }

        // shut down the service now because it is sure that now tasks are active
        // anymore
        service.shutdown();

        // log elapsed time
        LOG.info("Anzahl Additionen: " + numberOfINcAndDec);
        LOG.info("Anzahl Subtraktionen: " + numberOfINcAndDec);
        LOG.info("Zeit Atomic-Counter: " + timeElapsedAtomic);
        LOG.info("Zeit Synchronized-Counter: " + timeElapsedSynchronized);
        LOG.info("Simulation finished...");
    }
}
