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
        Map<String, Object> map = objectMapper.readValue(reader, new TypeReference<Map<String, Object>>(){});
        return map;
    }

    public static String generate(String filepath1, String filepath2) throws Exception{
        var map1 = getDataFromPath(filepath1);
        var map2 = getDataFromPath(filepath2);
        var keys1 = map1.keySet();
        var keys2 = map2.keySet();
        List<String> unionKeys = new ArrayList<String>(CollectionUtils.union(keys1, keys2));
        Collections.sort(unionKeys);
        var deletedKeys = new ArrayList<>(CollectionUtils.subtract(keys1, keys2));
        var addedKeys = new ArrayList<>(CollectionUtils.subtract(keys2, keys1));

        //System.out.println(unionKeys);
        var result = unionKeys.stream()
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
        System.out.println(result);
        return "";
    }
}
