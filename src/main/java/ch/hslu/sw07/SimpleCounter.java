package ch.hslu.sw07;

public final class SimpleCounter {
    private int count = 0;

    public synchronized int increment() {
        count ++;
        return count;
    }
}
