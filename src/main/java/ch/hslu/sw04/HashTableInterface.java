package ch.hslu.sw04;

public interface HashTableInterface<T> {

    /**
     * Adds an entry to the HashTable
     * @param entry
     * @return whether it worked
     */
    public boolean add(T entry);

    /**
     * Removes an entry from the HashTable
     * @param entry
     * @return whether it worked
     */
    public boolean remove(T entry);

    /**
     * Looks up if an entry exists in the Hashtable
     * @param entry
     * @return if the hashcode exists
     */
    public boolean search(T entry);

    /**
     * Looks up an entry and returns its index
     * @param entry
     * @return the index of the Item
     */
    public int getIndex(T entry);
}

/*
@startuml

interface HashtableInterface<T> {
public boolean add(T entry);
public boolean remove(T entry);
public boolean search(T entry);
public int getIndex(T entry);
}

@enduml
 */