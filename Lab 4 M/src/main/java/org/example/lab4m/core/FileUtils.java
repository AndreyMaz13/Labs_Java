package org.example.lab4m.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {

    public static String[] readFile(String fileName)
            throws IOException {

        List<String> lines =
                Files.readAllLines(
                        Path.of(fileName)
                );

        return lines.toArray(
                new String[0]
        );
    }

    public static void writeFile(
            String fileName,
            String[] data
    ) throws IOException {

        Files.write(
                Path.of(fileName),
                List.of(data)
        );
    }
}