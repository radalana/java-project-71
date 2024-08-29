package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

public class Parser {
    public static Map<String, Object> parse(String filepath) throws Exception {
        Path path = Paths.get(filepath);
        BufferedReader reader = Files.newBufferedReader(path);
        ObjectMapper objectMapper;
        String extension = FilenameUtils.getExtension(filepath);
        switch (extension) {
            case "json":
                objectMapper = new ObjectMapper();
                break;
            case "yml":
            case "yaml":
                objectMapper = new YAMLMapper();
                break;
            default:
                throw new Exception("Invalid file format");
        }
        return objectMapper.readValue(reader, new TypeReference<>() { });
    }
}
