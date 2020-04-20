package ch.hslu.sw09.prime;

import java.math.BigInteger;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeTesterTask implements Runnable {
    final static Logger LOG = LogManager.getFormatterLogger(PrimeTesterTask.class);
    private final PrimeCounter primeTask;

    public PrimeTesterTask(final PrimeCounter primeCounter) {
        this.primeTask = primeCounter;
    }

    @Override
    public void run() {
        while (!primeTask.enoughPrimes()) {
            // test BigIntegers for as long as one that is a prime is found, then finish
            if (new BigInteger(1024, new Random()).isProbablePrime(Integer.MAX_VALUE)) {
                primeTask.foundPrimeNumber();
                LOG.info("Found prime!");
            }
        }

    }

}