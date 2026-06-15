package org.example.lab4m.console;

import org.example.lab4m.core.DataProcessor;
import org.example.lab4m.core.FileUtils;

public class ConsoleApp {

    public static void main(String[] args) {

        try {

            String inputFile =
                    "test/input01.txt";

            String[] data =
                    FileUtils.readFile(inputFile);

            String[] result =
                    DataProcessor.processPipeline(
                            data
                    );

            System.out.println("Результат:");

            for (String s : result) {
                System.out.println(s);
            }

        } catch (Exception e) {

            System.out.println(
                    "Ошибка: " + e.getMessage()
            );

        }
    }
}