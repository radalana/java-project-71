package hexlet.code;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

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
        map1.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        return "";
    }
}
