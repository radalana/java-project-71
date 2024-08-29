package hexlet.code;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;

import java.util.List;

import java.util.Collections;
import java.util.ArrayList;
import static  hexlet.code.Node.Differ.DELETED;
import static  hexlet.code.Node.Differ.ADDED;
import static  hexlet.code.Node.Differ.CHANGED;
import static  hexlet.code.Node.Differ.UNCHANGED;

public class Differ {
    public static boolean isSameFormat(String file1, String file2) {
        String ext1 = FilenameUtils.getExtension(file1);
        String ext2 = FilenameUtils.getExtension(file2);
        return ext1.equalsIgnoreCase(ext2);
    };
    public static String generate(String filepath1, String filepath2) throws Exception {
        if (!isSameFormat(filepath1, filepath2)) {
            throw new Exception("Files must be of the same type");
        }
        var map1 = Parser.parse(filepath1);
        var map2 = Parser.parse(filepath2);
        var keys1 = map1.keySet();
        var keys2 = map2.keySet();
        List<String> unionKeys = new ArrayList<>(CollectionUtils.union(keys1, keys2));
        Collections.sort(unionKeys);
        var deletedKeys = new ArrayList<>(CollectionUtils.subtract(keys1, keys2));
        var addedKeys = new ArrayList<>(CollectionUtils.subtract(keys2, keys1));

        //System.out.println(unionKeys);
        var tree = unionKeys.stream()
                .map(key -> {
                    if (deletedKeys.contains(key)) {
                        return new Node(key, map1.get(key), DELETED);
                    } else if (addedKeys.contains(key)) {
                        return new Node(key, map2.get(key), ADDED);
                    } else {
                        if (map1.get(key).equals(map2.get(key))) {
                            return new Node(key, map1.get(key), UNCHANGED);
                        } else {
                            return new Node(key, map1.get(key), map2.get(key), CHANGED);
                        }
                    }
                }).toList();
        //System.out.println(tree);
        String intend = " ";
        var lines = tree.stream()
                .map(node  -> {
                    String line = switch (node.differ) {
                        case DELETED -> intend + String.format("- %s: %s", node.key, node.value);
                        case ADDED -> intend + String.format("+ %s: %s", node.key, node.value);
                        case CHANGED -> intend + String.format("- %s: %s\n%s+ %s: %s", node.key, node.value,
                                intend + intend, node.key, node.newValue);
                        case UNCHANGED -> intend + String.format("  %s: %s", node.key, node.value);
                        default -> "Invalid";
                    };
                    return intend + line;
                }).toList();
        String result = "{\n" + String.join("\n", lines) + "\n}";
        System.out.println(result);
        return result;
    }
}
