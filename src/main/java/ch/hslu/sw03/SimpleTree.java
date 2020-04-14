package ch.hslu.sw03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author robin
 * @param <T>
 */
public class SimpleTree<T extends Comparable<? super T>> implements TreeInterface<T>{

    private static final Logger LOG = LogManager.getLogger(SimpleTree.class);

    private Node root;
    private int size;
    private int searchCount;

    SimpleTree() {
        this.size = 0;
    }

    SimpleTree(T data) {
        this.size = 0;
        add(data);
    }

    /**
     * Hinzufügen von Elementen.
     *
     * @param data
     */
    @Override
    public void add(T data) {
        // First Element
        if (this.size == 0) {
            root = new Node(data);
            LOG.info("added root...");
            size++;
            return;
        }
        Node newNode = new Node(data);
        if (root.compareTo(newNode) <= 0) {
            LOG.info("go right...");
            root.setRightNode(newNode);
        } else if (root.compareTo(newNode) > 0 ) {
            LOG.info("go left...");
            root.setLeftNode(newNode);
        }
        size++;
    }

    /**
     * Entfernen von Elementen.
     *
     * @param data
     */
    @Override
    public void remove(T data) {
        // only possible if no children
        if (root.getLeftNode() == null && root.getRightNode() == null) {
            root.setData(null);
        }
    }

    public int getSize() {
        return size;
    }

    /**
     * Suchen von Elementen.
     *
     * @param data
     * @return
     */
    @Override
    public Node search(T data) {
        // InOrder
        LOG.info("Search initial started");
        Node iterationNode = root;
        searchCount = 0;
        return search(data, iterationNode);
    }

    private Node search(T data, Node iterationNode) {
        Node searchNode = new Node(data);
        searchCount++;
        // if root.data is greater than n that means we need to go to the left of root
        // if root.data is smaller than n that means we need to go to the right of root
        // if at any point in time root.data is equal to the n then we have found the node returning true
        // if we reach the leaves (end of tree) return false, node has not been found
        if (iterationNode.equals(searchNode)) {
            LOG.info("Value [" + searchNode.toString() + "] found.");
            return searchNode;
        } else if (iterationNode.compareTo(searchNode) <= 0) { // rechts
            LOG.info("Value to the right. Level: " + searchCount);
            if (iterationNode.getRightNode() != null) { // if node not null, go deeper
                search(data, iterationNode.getRightNode());
            }
        } else if (iterationNode.compareTo(searchNode) > 0) { // links
            LOG.info("Value to the left. Level: " + searchCount);
            if (iterationNode.getLeftNode() != null) { // if node not null, go deeper
                search(data, iterationNode.getLeftNode());
            }
        }
        return null; // if nothing is found return null
    }
}
/*
@startuml
class SimpleTree<T> {
- (static) final Logger : LOG
- Node : root
- int : size
- int : searchcount
+ SimpleTree()
+ void add(T)
+ void remove(T)
+ int getSize()
+ Node search(T)
- Node search(T, Node)
}
@endumlb
 */