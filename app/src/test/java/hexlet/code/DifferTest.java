package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DifferTest {
    @Test
    void generate() throws Exception {
        //путь файла = дать сразу
        //ожидаемы результат в файле сравнить содержимое файлов
        String path1 = "app/files/file1.json";
        String path2 = "files/file2.json";
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        try {
            String actual = Differ.generate(path1, path2);
            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
