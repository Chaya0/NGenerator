package com.generator.util;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class PomEditorUtil {

    public static void addDependencyToPom(String pomFilePath, String dependencyXml) throws Exception {
        // Load the pom.xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(pomFilePath));

        // Normalize the XML structure
        doc.getDocumentElement().normalize();

        // Find the <dependencies> tag
        NodeList dependenciesList = doc.getElementsByTagName("dependencies");
        if (dependenciesList.getLength() == 0) {
            System.out.println("No <dependencies> tag found in pom.xml");
            return;
        }

        Node dependenciesNode = dependenciesList.item(0);

        // Convert the dependency string to XML Node
        DocumentBuilder tempBuilder = factory.newDocumentBuilder();
        Document tempDoc = tempBuilder.parse(new java.io.ByteArrayInputStream(dependencyXml.getBytes()));
        Node dependencyNode = doc.importNode(tempDoc.getDocumentElement(), true);

        // Append the new dependency node to <dependencies>
        dependenciesNode.appendChild(dependencyNode);

        // Write the updated XML back to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(pomFilePath));
        transformer.transform(source, result);

        System.out.println("Dependency added successfully.");
    }

}
