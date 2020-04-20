package ch.hslu.sw09.count;

public interface Count {
    /**
     * Zähler um 1 addieren.
     */
    public void increment();

    /**
     * Zähler um 1 subtrahieren.
     */
    public void decrement();

    /**
     * Liefert den Zählerstand.
     * @return Zählerstand.
     */
    public int get();
}
