# Use Cases

## Define Application Model in XML

- The user specifies the application's entities, attributes, relationships, and behaviors in an XML format.
- This involves creating structured representations of the application's data and logic, which will be used as the basis for code generation in both the backend and frontend layers.

## Define Generator Properties

- The user configures the settings and parameters for the code generation process. 
- This includes selecting the desired architectural pattern (e.g., monolithic or microservices), setting options for frontend frameworks (e.g., Angular, React, or Vaadin), specifying service layers, repository types, database configurations, and additional preferences for code customization.

## Generate Application

- User generates backend and frontend code for the application based on the XML model.
- System generates documentation for the APIs using Swagger or GraphQL introspection.

## Open Generated Project in IDE

User opens the generated project in their preferred IDE.

## Generate Database Schema Via JPA

- User generates the database schema from the model.
- System communicates with the database to create the schema.

## Customize Generated Code

- User modifies generated code and preserves changes during regeneration.

## Preserve Custom Code

- System preserves custom code during regeneration.
