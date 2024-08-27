package hexlet.code;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import static hexlet.code.Node.Differ.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static Map<String, Object> getDataFromPath(String filepath) throws Exception{
        Path path = Paths.get(filepath);
        BufferedReader reader = Files.newBufferedReader(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(reader, new TypeReference<>(){});
    }

    public static String generate(String filepath1, String filepath2) throws Exception{
        var map1 = getDataFromPath(filepath1);
        var map2 = getDataFromPath(filepath2);
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
                    }else if (addedKeys.contains(key)) {
                        return new Node(key, map2.get(key), ADDED);
                    }else{
                        if (map1.get(key).equals(map2.get(key))) {
                            return new Node(key, map1.get(key), UNCHANGED);
                        }else {
                            return new Node(key, map1.get(key), map2.get(key), CHANGED);
                        }
                    }
                }).toList();
        //System.out.println(tree);
        String intend = " ";
        var lines = tree.stream()
                .map(node  -> {
                    String line = switch(node.differ) {
                       case DELETED -> intend + String.format("- %s: %s", node.key, node.value);
                       case ADDED -> intend +String.format("+ %s: %s", node.key, node.value);
                       case CHANGED -> intend + String.format("- %s: %s\n%s+ %s: %s", node.key, node.value, intend + intend, node.key, node.newValue);
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
