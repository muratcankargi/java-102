package Generic.Task;

import java.util.ArrayList;

public class MyList<T> {
    private int capasity;
    ArrayList<T> arr = new ArrayList<>();

    public MyList() {
        this.capasity = 10;
    }

    public MyList(int capasity) {
        this.capasity = capasity;
    }

    public int size() {
        return arr.size();
    }

    public Integer getCapacity() {
        return this.capasity;
    }

    public void add(T data) {

        arr.add(data);
        if (arr.size() > capasity) {
            this.capasity += 10;
        }
    }

    public T get(int index) {
        return arr.get(index);
    }

    public T remove(int index) {
        if (index > (arr.size() - 1) || index < 0) {
            return null;
        }
        return arr.remove(index);
    }

    public T set(int index, T data) {
        if (index > (arr.size() - 1) || index < 0) {
            return null;
        }
        return arr.set(index, data);
    }

    public String toString() {
        return arr.toString();
    }

    public int indexOf(T data) {
        return arr.indexOf(data);
    }

    public int lastIndexOf(T data) {
        return arr.lastIndexOf(data);
    }

    public boolean isEmpty() {
        return arr.size() == 0;
    }

    public T[] toArray() {
        T[] a;
        a = (T[]) arr.toArray();
        return a;
    }

    public void clear() {
        arr.clear();
    }

    public MyList<T> subList(int start, int finish) {
        MyList<T> a = new MyList<>();

        for(int i=start;i<=finish;i++){
            a.add(arr.get(i));
        }
        return a;
    }
    public boolean contains(T data){
        for(T value:arr){
            if(value==data)
                return true;
        }
        return false;
    }

}
