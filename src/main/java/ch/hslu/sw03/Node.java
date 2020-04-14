package ch.hslu.sw03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 *
 * @author Robin
 * @param <T>
 */
// <T extends Comprable<T>> stellt sicher, dass der generische Type Comparable implementiert hat, sonst kein initialisieren möglich
public class Node<T extends Comparable<? super T>> implements Comparable<Node<T>> { // https://stackoverflow.com/questions/4830400/java-unchecked-call-to-comparetot-as-a-member-of-the-raw-type-java-lang-compa

    private static final Logger LOG = LogManager.getLogger(Node.class);
    private Node leftNode;
    private Node rightNode;
    private Data data;

    private int searchCount;

    Node(T data) {
        this.data = new Data(data);
        this.leftNode = null;
        this.rightNode = null;
        this.searchCount = 0;
    }

    public void setRightNode(Node rightNode) {
        searchCount++;

        if (this.rightNode == null) {
            LOG.info("Added Value to the right.");
            this.rightNode = rightNode;
        } else if (this.rightNode.compareTo(rightNode) > 0 ) { // Rekursion wenn der Node nicht null ist
            LOG.info("go left...");
            this.rightNode.setLeftNode(rightNode);
        } else if (this.rightNode.compareTo(rightNode) <= 0) {
            LOG.info("go right...");
            this.rightNode.setRightNode(rightNode);
        }
    }

    public void setLeftNode(Node leftNode) {
        searchCount++;

        if (this.leftNode == null) {
            LOG.info("Added Value to the left.");
            this.leftNode = leftNode;
        } else if (this.leftNode.compareTo(leftNode) > 0) {
            LOG.info("go left...");
            this.leftNode.setLeftNode((leftNode));
        } else if (this.leftNode.compareTo(leftNode) <= 0) {
            LOG.info("gor right...");
            this.leftNode.setLeftNode(leftNode);
        }
    }

    public void setData(T data) {
        this.data.setData(data);
    }

    public Node getRightNode() {
        return this.rightNode;
    }

    public Node getLeftNode() {
        return this.leftNode;
    }

    public T getData() {
        return (T) this.data.getData();
    }

    @Override
    public int compareTo(Node<T> o) {
        return data.compareTo(o.getData());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o; // <?> is a shorthand for <? extends Object>, it's also known as an unbounded wildcard. So you can specify any type of object in your generic.
        return Objects.equals(getLeftNode(), node.getLeftNode()) &&
                Objects.equals(getRightNode(), node.getRightNode()) &&
                Objects.equals(getData(), node.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftNode(), getRightNode(), getData());
    }

    @Override
    public String toString() {
        return "Node{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                ", data=" + data +
                ", searchCount=" + searchCount +
                '}';
    }
}

/*
@startuml
class Node<T> {
- (static) final Log : Logger
- leftNode : Node
- rightNode : Node
- data : Data
- searchCount : int
+ Node(E)
+ void setRightNode(Node)
+ void setLeftNode(Node)
+ void setData(E)
+ Node getrightData()
+ Node getLeftNode()
+ getData()
+ int hashCode()
+ bool equals(Object)
+ int compareTo(Node)
+ String toString()
}
@enduml
 */
