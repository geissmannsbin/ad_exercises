package ch.hslu.sw01;

import java.util.ArrayList;

public class Fibonacci {

    ArrayList results = new ArrayList();
    Fibonacci(){

    }

    public int fiboRec1(final int a){
        if (a < 0){
            throw new IllegalArgumentException("Argument must be greater than zero: " + a);
        }
        if (a==1||a==2) return 1; //Rekursionsbasis
        else return fiboRec1(a-1) + fiboRec1(a-2); //Rekursionsvorschrift
    }

    public int fiboRec2(final int a){
        if (a < 0){
            throw new IllegalArgumentException("Argument must be greater than zero: " + a);
        }
        if (a==1||a==2) return 1; //Rekursionsbasis
        else return fiboRec1(a-1) + fiboRec1(a-2); //Rekursionsvorschrift
    }

    public int fiboIte(final int a){
        if (a < 0){
            throw new IllegalArgumentException("Argument must be greater than zero: " + a);
        }
        int fib = 1;
        int prevFib = 1;

        for (int i=2; i<a; i++){
            int temp = fib;
            fib+= prevFib;
            prevFib = temp;
        }
        return fib;
    }
}
