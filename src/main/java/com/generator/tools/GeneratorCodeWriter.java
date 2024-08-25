package com.generator.tools;

public class GeneratorCodeWriter {

    private static final String INDENTATION = "    "; // 4 spaces per indentation level

    public static void printCode(String code) {
        String[] lines = code.split("\\r?\\n");
        int indentLevel = 0;

        for (String line : lines) {
            String trimmedLine = line.trim();

            // Handle line with opening brace
            if (trimmedLine.endsWith("{")) {
                fileWriteln(indentLevel, trimmedLine);
                indentLevel++;
            } 
            // Handle line with closing brace
            else if (trimmedLine.endsWith("}")) {
                indentLevel--;
                fileWriteln(indentLevel, trimmedLine);
            } 
            // Handle empty lines or other lines
            else {
                fileWriteln(indentLevel, trimmedLine);
            }
        }
    }

    private static void fileWriteln(int indentLevel, String line) {
        String indent = getIndentation(indentLevel);
        // Print formatted output with indentation
        System.out.println("file.writeln(" + indentLevel + ", \"" + replaceIndentation(indent + line) + "\");");
    }

    private static String getIndentation(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(INDENTATION);
        }
        return sb.toString();
    }

    public static String replaceIndentation(String input) {
        // Trim the leading and trailing whitespace
        String trimmed = input.trim();
        // Replace multiple spaces or tabs with a single space
        return trimmed.replaceAll("[\\s]+", " ");
    }

    public static void main(String[] args) {

        printCode("");
    }
}