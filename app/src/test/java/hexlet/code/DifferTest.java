package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    @Test
    void generate() throws Exception {
        try {
            URL resource1 = getClass().getClassLoader().getResource("file1.json");
            URL resource2 = getClass().getClassLoader().getResource("file2.json");

            if (resource1 == null || resource2 == null) {
                fail("Test failed because the resource files could not be found.");
            }

            String path1 = resource1.getPath();
            String path2 = resource2.getPath();

            Path expected = Paths.get(getClass().getClassLoader().getResource("expected1.txt").toURI());
            String actual = Differ.generate(path1, path2);
            assertThat(expected).hasContent(actual);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }
    @Test
    void generateYML() throws Exception {
        try {
            URL resource1 = getClass().getClassLoader().getResource("file1.yml");
            URL resource2 = getClass().getClassLoader().getResource("file2.yml");

            if (resource1 == null || resource2 == null) {
                fail("Test failed because the resource files could not be found.");
            }

            String path1 = resource1.getPath();
            String path2 = resource2.getPath();
            String actual = Differ.generate(path1, path2);
            Path expected = Paths.get(getClass().getClassLoader().getResource("expected1.txt").toURI());
            assertThat(expected).hasContent(actual);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
