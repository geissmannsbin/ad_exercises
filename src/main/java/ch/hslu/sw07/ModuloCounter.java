package ch.hslu.sw07;

public final class ModuloCounter {
    private int count = 0;
    private final int mod;

    public ModuloCounter(final int mod){
        this.mod = mod;
    }

    public int getValue() {
        return count;
    }

    public synchronized void increment() {
        count = (count + 1) % mod;
    }

    public synchronized void decrement() {
        count = (count - 1 + mod) % mod;
    }
}
