package ch.hslu.sw03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    void testAdd() {
        SimpleTree<String> myTree = new SimpleTree<>();
        myTree.add("4");
        myTree.search("4");
    }
}