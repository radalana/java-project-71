package hexlet.code;

import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static boolean isSameFormat(String file1, String file2) {
        String ext1 = FilenameUtils.getExtension(file1);
        String ext2 = FilenameUtils.getExtension(file2);
        return ext1.equalsIgnoreCase(ext2);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        if (!isSameFormat(filepath1, filepath2)) {
            throw new Exception("Files must be of the same type");
        }
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);
        var tree = DiffStructure.build(data1, data2);
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
