package ch.hslu.sw09.count;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.Callable;

public class CountingTask implements Callable<Long> {
    private static final Logger LOG = LogManager.getLogger(CountingTask.class);
    private final Count counter;
    private final int nbrOfIncr;
    private final int nbrOfDec;

    public CountingTask(final int nmbrOfIncr, final int nbrOfDec, Count counter) {
        this.counter = counter;
        this.nbrOfIncr = nmbrOfIncr;
        this.nbrOfDec = nbrOfDec;
    }

    @Override
    public Long call() {
        long start = 0;
        long finish = 0;
        try {
            start = System.currentTimeMillis();
            for (int i = 0; i < nbrOfIncr; i++) {
                counter.increment();
            }
            for (int i = 0; i < nbrOfDec; i++) {
                counter.decrement();
            }
            finish = System.currentTimeMillis();

        } catch (Exception e) {
            LOG.error(e);
        }

        return (finish - start);
    }

}