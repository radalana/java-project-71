package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DifferTest {
    @Test
    void generate() throws Exception {
        try {
            URL resource1 = getClass().getClassLoader().getResource("files/file1.json");
            URL resource2 = getClass().getClassLoader().getResource("files/file2.json");

            if (resource1 == null || resource2 == null) {
                fail("Test failed because the resource files could not be found.");
            }

            String path1 = resource1.getPath();
            String path2 = resource2.getPath();

            String expected = "{\n"
                    + "  - follow: false\n"
                    + "    host: hexlet.io\n"
                    + "  - proxy: 123.234.53.22\n"
                    + "  - timeout: 50\n"
                    + "  + timeout: 20\n"
                    + "  + verbose: true\n"
                    + "}";

            String actual = Differ.generate(path1, path2);
            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
