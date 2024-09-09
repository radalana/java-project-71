package hexlet.code;

import org.apache.commons.io.FilenameUtils;

public class Differ {
    public static boolean isSameFileType(String file1, String file2) {
        String ext1 = FilenameUtils.getExtension(file1);
        String ext2 = FilenameUtils.getExtension(file2);
        return ext1.equalsIgnoreCase(ext2);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        if (!isSameFileType(filepath1, filepath2)) {
            throw new Exception("Files must be of the same type");
        }
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);
        var tree = DiffStructure.build(data1, data2);
        var result = Formatter.format(tree, format);
        System.out.println(result);
        return result;
    }

    public static String generate(String filepath1, String filepath2) throws Exception{
        return generate(filepath1, filepath2, "stylish");
    }

}
