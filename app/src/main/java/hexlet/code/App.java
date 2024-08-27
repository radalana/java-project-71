package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, description = "Output format", defaultValue = "stylish")
    private String format;
    @Parameters(index = "0", description = "past to first file")
    private String filepath1;

    @Parameters(index = "1", description = "past to second file")
    private String filepath2;

    @Override
    public Integer call() throws Exception {
        Differ.generate(filepath1, filepath2);
        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
