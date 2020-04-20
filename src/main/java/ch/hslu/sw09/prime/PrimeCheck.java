/*
 * Copyright 2020 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.sw09.prime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/**
 * 100 grosse Primzahlen produzieren.
 */
public final class PrimeCheck {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.sw09.prime.PrimeCheck.class);

    /**
     * Privater Konstruktor.
     */
    public PrimeCheck() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int howManyPrimes = 1000;

        LOG.info("----------------------------------");
        LOG.info("Demo Implementation");
        LOG.info("----------------------------------");
        long timer = System.currentTimeMillis();

        int n = 1;
        while (n <= howManyPrimes) {
            BigInteger bi = new BigInteger(1024, Integer.MAX_VALUE, new Random());
            if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                LOG.info(n + ": " + bi.toString().substring(0, 20) + "...");
                n++;
            }
        }

        long duration = System.currentTimeMillis() - timer;
        LOG.info("Execution Time: " + duration + "ms");
        LOG.info("----------------------------------");

        implementation1(howManyPrimes);

        implementation2(howManyPrimes);

        implementation3(howManyPrimes);
    }

    public static void implementation3(final int howManyPrimes) throws InterruptedException, ExecutionException{
        LOG.info("----------------------------------");
        LOG.info("Robins Implementation 2");
        LOG.info("----------------------------------");
        long timer = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(1000);

        ArrayList<Callable<BigInteger>> tasks = new ArrayList<>();

        for(int i = 0; i < howManyPrimes; i++)
            tasks.add(() -> {
                boolean found;
                BigInteger currentNumber;
                do {
                    currentNumber = new BigInteger(1024, new Random());
                    found = currentNumber.isProbablePrime(Integer.MAX_VALUE);
                } while (!found);
                return currentNumber;
            });

        List<Future<BigInteger>> results =  executor.invokeAll(tasks);

        int received = 0;
        for(Future<BigInteger> result: results){
            LOG.info("Prime No. " + received + " : " + result.get().toString().substring(0, 20) + "...");
            received ++;
        }

        executor.shutdown();

        long duration = System.currentTimeMillis() - timer;
        LOG.info("Execution Time: " + duration + "ms");
        LOG.info("----------------------------------");
    }

    public static void implementation2(final int howManyPrimes) throws InterruptedException {

        LOG.info("----------------------------------");
        LOG.info("Robins Implementation 2");
        LOG.info("----------------------------------");
        long timer = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        CompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executor);

        for(int i = 0; i < howManyPrimes; i++) {
            completionService.submit(() -> {
                boolean found;
                BigInteger currentNumber;
                do {
                    currentNumber = new BigInteger(1024, new Random());
                    found = currentNumber.isProbablePrime(Integer.MAX_VALUE);
                } while (!found);
                return currentNumber;
            });
        }

        int received = 0;

        while(received < howManyPrimes) {
            Future<BigInteger> resultFuture = completionService.take(); //blocks if none available
            try {
                LOG.info("Prime No. " + received + " : " + resultFuture.get().toString().substring(0, 20) + "...");
                received ++;
            }
            catch(Exception e) {
                // Jajajaja
            }
        }
        executor.shutdown();
        long duration = System.currentTimeMillis() - timer;
        LOG.info("Execution Time: " + duration + "ms");
        LOG.info("----------------------------------");
    }

    public static void implementation1(final int howManyPrimes) throws InterruptedException, ExecutionException{
        LOG.info("----------------------------------");
        LOG.info("Robins Implementation 1");
        LOG.info("----------------------------------");
        long timer = System.currentTimeMillis();

        final Callable<BigInteger> bigPrime = () -> {
            boolean found;
            BigInteger currentNumber;
            do {
                currentNumber = new BigInteger(1024, new Random());
                found = currentNumber.isProbablePrime(Integer.MAX_VALUE);
            } while (!found);
            return  currentNumber;
        };

        final ExecutorService executor = Executors.newFixedThreadPool(100);
        final ArrayList<Future<BigInteger>> futures = new ArrayList<>();

        for (int i = 0; i < howManyPrimes; i++) {
            final Future<BigInteger> future = executor.submit(bigPrime);
            futures.add(future);
        }

        int count = 0;
        for(Future<BigInteger> future: futures){
            count++;
            LOG.info("Prime No. " + count + " : " + future.get().toString().substring(0, 20) + "...");
        }
        executor.shutdown();

        long duration = System.currentTimeMillis() - timer;
        LOG.info("Execution Time: " + duration + "ms");
        LOG.info("----------------------------------");
    }
}
