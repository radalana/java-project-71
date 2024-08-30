package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;


public class Stylish {
    public static String style(List<Node> tree) {
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
        //System.out.println(result);
        return result;
    }
}
