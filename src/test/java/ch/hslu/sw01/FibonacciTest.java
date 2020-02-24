package ch.hslu.sw01;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testExpectedException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Fibonacci Fibo = new Fibonacci();
            Fibo.fiboRec1(-50);
        });
    }

    @Test
    void testFiboRec1(){
        Fibonacci Fibo = new Fibonacci();
        Assertions.assertEquals(1, Fibo.fiboRec1(2));
    }

    @AfterEach
    void tearDown() {
    }
}