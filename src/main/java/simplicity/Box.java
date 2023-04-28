package simplicity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box<T extends Objek> {
    private final Map<String, Integer> map;
    private final List<T> list;
    private T type;
    private int length;

    public Box() {
        this(new ArrayList<>());
    }

    public Box(List<T> list) {
        length = 0;
        map = new HashMap<>();
        this.list = list;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public List<T> getList() {
        return list;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public int getCount(String objekName) {
        return map.getOrDefault(objekName, 0);
    }

    public boolean isNotEmpty(String objekName) {
        return getCount(objekName) > 0;
    }

    public boolean isEmpty(String objekName) {
        return getCount(objekName) == 0;
    }

    public void add(T t) {
        list.add(t);
        if (getCount(t.getObjekName()) > 0)
            map.put(t.getObjekName(), map.get(t.getObjekName()) + 1);
        else
            map.put(t.getObjekName(), 1);
        length++;
    }

    public void delete(T t) {
        if (getCount(t.getObjekName()) > 0) {
            list.remove(t);
            map.put(t.getObjekName(), map.get(t.getObjekName()) - 1);
            length--;
        }
        if (getCount(t.getObjekName()) == 0)
            map.remove(t.getObjekName());
    }
}
