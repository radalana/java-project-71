package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<Node> data, String format) {
        switch (format) {
            case "stylish":
                return Stylish.style(data);
            default:
                throw new RuntimeException();
        }
    }
}
