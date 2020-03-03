package ch.hslu.sw03;

import java.util.Objects;

/**
 *
 * @author Robin
 * @param <T> Type
 */

// <T extends Comprable<T>> stellt sicher, dass der generische Type Comparable implementiert hat, sonst kein initialisieren möglich
public class Data<T extends Comparable<? super T>> implements Comparable<T> {

    private T data;

    Data(T data){
        this.data = data;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data<?> data1 = (Data<?>) o;
        return Objects.equals(getData(), data1.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }

    @Override
    public int compareTo(T o) {
        return (data).compareTo(o);
    }
}

/*
@startuml
class Data<E> {
- data: E
+ Data(E)
+ void setData(E)
+ E getData()
+ int hashCode()
+ boolean equals(Object)
+ int compareTo(E)
+ String toString()
}
@enduml
 */