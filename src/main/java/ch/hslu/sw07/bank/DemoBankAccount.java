/*
 * Copyright 2020 Hochschule Luzern - Informatik.
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
package ch.hslu.sw07.bank;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstration der Bankgeschäfte - Aufgabe 2 - N1_EX_ThreadsSynch.
 */
public final class DemoBankAccount {

    private static final Logger LOG = LogManager.getLogger(DemoBankAccount.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBankAccount() {
    }

    /**
     * Wartet bis alle Threads abgearbeitet sind.
     *
     * @param threads Array mit Threads.
     * @throws InterruptedException Interupted.
     */
    private static void waitForCompletion(final Thread[] threads) throws InterruptedException {
        for (final Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        final ArrayList<BankAccount> source = new ArrayList<>();
        final ArrayList<BankAccount> target = new ArrayList<>();
        // Amount per cash transfer
        final int amount = 1000;
        // Count of Accounts, amounts to times 2
        final int number = 1000;

        // create the source and target accounts
        for (int i = 0; i < number; i++) {
            source.add(new BankAccount(amount));
            target.add(new BankAccount());
        }

        // create as many threads as there are accounts and start the transfer afterwards
        final Thread[] threads = new Thread[number * 2];
        for (int i = 0; i < number; i++) {
            threads[i] = new Thread(new AccountTask(source.get(i), target.get(i), amount));
            threads[i + number] = new Thread(new AccountTask(target.get(i), source.get(i), amount));
        }
        for (final Thread thread : threads) {
            thread.start();
        }
        for (final Thread thread : threads) {
            thread.join();
        }
        LOG.info("Bank accounts after transfers");
        for (int i = 0; i < number; i++) {
            LOG.info("source({}) = {}; target({}) = {};", i, source.get(i).getBalance(), i, target.get(i).getBalance());
        }
    }
}
