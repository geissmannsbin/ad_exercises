package ch.hslu.sw03;

/**
 * @author Robin Geissmann
 * @param <T>
 */
public interface TreeInterface<T> {

    /**
     * Hinzufügen von Elementen.
     *
     * @param data
     */
    public void add(T data);

    /**
     * Entfernen von Elementen.
     *
     * @param data
     */
    public void remove(T data);

    /**
     * Suchen von Elementen.
     *
     * @param data
     * @return
     */
    public Node search(T data);
}

/*
@startuml
interface TreeInterface<T> {
+ void add(T)
+ void remove(T)
+ Node search(T)
}
@enduml
*/


