package hexlet.code;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static  hexlet.code.Node.Differ.DELETED;
import static  hexlet.code.Node.Differ.ADDED;
import static  hexlet.code.Node.Differ.CHANGED;
import static  hexlet.code.Node.Differ.UNCHANGED;

public class DiffStructure {
    public static List<Node> build(Map<String, Object> data1, Map<String, Object> data2) {
        var keys1 = data1.keySet();
        var keys2 = data2.keySet();
        List<String> unionKeys = new ArrayList<>(CollectionUtils.union(keys1, keys2));
        Collections.sort(unionKeys);
        var deletedKeys = new ArrayList<>(CollectionUtils.subtract(keys1, keys2));
        var addedKeys = new ArrayList<>(CollectionUtils.subtract(keys2, keys1));

        return unionKeys.stream()
                .map(key -> {
                    if (deletedKeys.contains(key)) {
                        return new Node(key, data1.get(key), DELETED);
                    } else if (addedKeys.contains(key)) {
                        return new Node(key, data2.get(key), ADDED);
                    } else {
                        if (data1.get(key).equals(data2.get(key))) {
                            return new Node(key, data1.get(key), UNCHANGED);
                        } else {
                            return new Node(key, data1.get(key), data2.get(key), CHANGED);
                        }
                    }
                }).toList();
    }

}
