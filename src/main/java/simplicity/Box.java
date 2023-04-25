package simplicity;

import java.util.HashMap;
import java.util.Map;

public class Box<T> {
    private T type;
    private Map<T, Integer> mapT;
    public int length = 0;

    public Box() {
        mapT = new HashMap<T, Integer>();
    }

    public Box(Map<T, Integer> mapT) {
        this.mapT = mapT;
    }

    public T getType() {
        return type;
    }

    public Map<T, Integer> getMapT() {
        return mapT;
    }

    public void setType(T type) {
        this.type = type;
    }

    public void setMapT(Map<T, Integer> mapT) {
        this.mapT = mapT;
    }

    public void add(T t) {
        if (mapT.containsKey(t)) {
            mapT.put(t, mapT.get(t) + 1);
            length++;
        } else
            mapT.put(t, 1);
    }

    public void delete(T t) {
        if (mapT.containsKey(t))
            if (mapT.get(t) == 1) {
                mapT.remove(t);
                length--;
            } else
                mapT.put(t, mapT.get(t) - 1);
    }
}
