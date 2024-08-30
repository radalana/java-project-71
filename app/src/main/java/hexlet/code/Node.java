package hexlet.code;

import java.util.List;

public class Node {
    public enum Differ {
        ADDED,
        DELETED,
        UNCHANGED,
        CHANGED,
    }
    public String key;
    public Object value;
    public Object newValue;
    public Differ differ; //возможно нужен enum

    Node(String key, Object value, Differ differ) {
        this.key = key;
        this.value = value;
        this.differ = differ;
    }
    Node(String key, Object oldValue, Object newValue, Differ differ) {
        this.key = key;
        this.value = oldValue;
        this.newValue = newValue;
        this.differ = differ;
    }
    @Override
    public String toString() {
        if (this.differ.equals(Differ.CHANGED)) {
            return String.format("key = %s, old value = %s, new value = %s, %s\n", key, value, newValue, differ);
        }
        return String.format("key = %s, value = %s, %s\n", key, value, differ);
    }
}
