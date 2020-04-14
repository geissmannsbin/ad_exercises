package ch.hslu.sw03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        LOG.info(("Start Tree Test Methode"));

        LOG.debug("Tree initialisieren");

        SimpleTree<String> myTree = new SimpleTree<>();

        LOG.debug("Werte hinzufügen (4,7,2,3,1,8,6)");
        LOG.info("add Value 4");
        myTree.add("4");
        LOG.info("add Value 7");

        myTree.add("7");
        LOG.info("add Value 2");

        myTree.add("2");
        LOG.info("add Value 3");

        myTree.add("3");
        LOG.info("add Value 1");

        myTree.add("1");
        LOG.info("add Value 8");

        myTree.add("8");
        LOG.info("add Value 6");

        myTree.add("6");

        LOG.info("add Value 9");

        myTree.add("9");

        LOG.debug("Werte suchen (8,1,2,7,4,9)");
        myTree.search("8");
        myTree.search("1");

        myTree.search("2");
        myTree.search("7");
        myTree.search("4");
        myTree.search("9");

        LOG.info("Der Baum enthält ["+myTree.getSize() +"] Elemente.");
    }
}
