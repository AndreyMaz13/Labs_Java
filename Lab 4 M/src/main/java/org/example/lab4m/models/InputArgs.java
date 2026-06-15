package org.example.lab4m.models;

public class InputArgs {

    private String inputFile;
    private String outputFile;

    public InputArgs(String[] args) {

        for (int i = 0; i < args.length - 1; i++) {

            if (args[i].equals("-i")) {

                inputFile = args[i + 1];
            }

            if (args[i].equals("-o")) {

                outputFile = args[i + 1];
            }
        }
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}