package com.generator.model.enums.properties;

public enum DependencyCode {
    SWAGGER("org.springdoc", "springdoc-openapi-starter-webmvc-ui"),
    JWT("io.jsonwebtoken", "jjwt");

    private String groupId;
    private String artifactId;

    private DependencyCode(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    // Method to get the code without a version
    public String getCode() {
        return String.format(
            "\t\t<dependency>\r\n" +
            "\t\t\t<groupId>%s</groupId>\r\n" +
            "\t\t\t<artifactId>%s</artifactId>\r\n" +
            "\t\t</dependency>\n",
            groupId, artifactId
        );
    }

    // Method to get the code with a version
    public String getCode(String version) {
        return String.format(
            "\t\t<dependency>\r\n" +
            "\t\t\t<groupId>%s</groupId>\r\n" +
            "\t\t\t<artifactId>%s</artifactId>\r\n" +
            "\t\t\t<version>%s</version>\r\n" +
            "\t\t</dependency>\n",
            groupId, artifactId, version
        );
    }
}
