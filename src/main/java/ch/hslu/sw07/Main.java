package ch.hslu.sw07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
       final int nr = Runtime.getRuntime().availableProcessors();
       LOG.info("available processors " + nr);

       final Thread self = Thread.currentThread();
       LOG.info("Name     :" + self.getName());
       LOG.info("Priority :" + self.getPriority());
       LOG.info("ID       :" + self.getId());
       LOG.info("Deamon?  :" + self.isDaemon());
    }
}
