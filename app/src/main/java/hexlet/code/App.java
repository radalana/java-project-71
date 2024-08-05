package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.lang.Runnable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = { "-f", "--format" }, description = "Output format", defaultValue = "stylish")
    private String format;
    @Parameters(index = "0", description = "past to first file")
    private String filepath1;

    @Parameters(index = "1", description = "past to second file")
    private String filepath2;

    @Override public void run() {}
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
