package com.generator.tools;

public class GeneratorCodeWriter {

	public void writeSideNavComponent(String code) {
        System.out.println("try (GeneratorOutputFile file = new GeneratorOutputFile(\"\" + \"/side-nav.component.ts\")) {");
        String[] lines = code.split("\\r?\\n");
        int indentLevel = 0;

        for (String line : lines) {
            System.out.println("\tfile.writeln(" + indentLevel + ", \"" + escapeQuotes(line) + "\");");

            if (line.trim().endsWith("{")) {
                indentLevel++;
            } else if (line.trim().startsWith("}")) {
                indentLevel--;
            }
        }

        System.out.println("}");
    }

    private String escapeQuotes(String line) {
        return line.replace("\"", "\\\"");
    }
}