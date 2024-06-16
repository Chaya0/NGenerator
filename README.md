# Spring Boot Application Generator

The Generator Spring Boot Application is a tool designed to streamline the process of generating Spring Boot applications based on a data model. It allows developers to quickly scaffold Spring Boot projects with CRUD operations, RESTful APIs, and other essential components, saving time and effort in application development.

## Features

- **Automated Code Generation**: The generator automates the process of creating Spring Boot applications based on a provided data model, eliminating the need for manual coding.
- **Support for Data Models**: Developers can define their data models using XML files, specifying entities, attributes, relationships, and other necessary details.
- **CRUD Operations**: The generated applications come with built-in support for CRUD (Create, Read, Update, Delete) operations for each entity defined in the data model.
- **RESTful APIs**: RESTful APIs are automatically generated for interacting with the application, providing seamless integration with frontend or external systems.
- **Customization Options**: Developers have the flexibility to customize the generated code to suit their specific requirements, including modifying templates, adding additional functionality, or integrating third-party libraries.
- **Integration with Frontend Frameworks**: The generator can be extended to support integration with frontend frameworks like Angular, React, or Vue.js, enabling full-stack application development.

## Usage

To use the Generator Spring Boot Application, follow these steps:

1. **Define Data Model**: Create an XML file defining the data model for your application. Specify entities, attributes, relationships, and any other necessary details.
2. **Define Application Properties**: Create an application.properties file and defining the properties for application that will be generated (Spring Boot version, project path, dependencies, database informations,...).
3. **Run the Generator**: Execute the generator application, providing the path to the XML data model file as input.
4. **Customize (Optional)**: Optionally, customize the generated code to add additional functionality or modify existing templates to meet specific requirements.
5. **Build and Run**: Build the generated Spring Boot application using your preferred build tool (e.g., Maven or Gradle) and run it locally or deploy it to a server.

## Model And Application Properties Configuration Guide

The `application.properties` file defines the necessary properties needed for generating and setting up the project.
The XML model defines the entities, enums, and properties needed for generating code and setting up the project.

### Application Properties Structure

##### `<properties>`

Generator properties used to configure the Spring application.

#### `bootVersion`
- **Description:** Specifies the version of Spring Boot to be used in the project.
- **Example:** `bootVersion=3.2.6`

#### `baseDir`
- **Description:** Directory name of the application folder.
- **Example:** `baseDir=MyProject`

#### `groupId`
- **Description:** Group name for the application.
- **Example:** `groupId=com.organisation`

#### `artifactId`
- **Description:** Artifact name for the application.
- **Example:** `artifactId=myProject`

#### `name`
- **Description:** Application name.
- **Example:** `name=MyProject`

#### `description`
- **Description:** Description of the application.
- **Example:** `description=My Test Project`

#### `packageName`
- **Description:** Package name of the application.
- **Example:** `packageName=com.organisation.myProject`

#### `javaVersion`
- **Description:** Java version to be used in the project.
- **Example:** `javaVersion=17`

#### `projectPath`
- **Description:** Path where the project will be created.
- **Example:** `projectPath=C:/projects/MyProject/`

#### `datasourceUrl`
- **Description:** Database connection URL.
- **Example:** `datasourceUrl=jdbc:mysql://localhost:3306/si?serverTimezone=UTC`

#### `datasourceUsername`
- **Description:** Database username.
- **Example:** `datasourceUsername=root`

#### `datasourcePassword`
- **Description:** Database password.
- **Example:** `datasourcePassword=`

### XML Structure

#### `<app>`

The root element of the XML file.

##### `<entities>`

A list of all entities in the application.

###### `<entity>`

Defines an entity.

- `name` (required): The name of the entity.
- `inherits` (optional): The name of the base entity this entity inherits from.
- `inheritanceType` (optional): The type of inheritance. Options are `SINGLE_TABLE`, `JOINED`, `TABLE_PER_CLASS`.
- `generationType` (optional): The generation type for ID. Options are `IDENTITY`, `SEQUENCE`, `TABLE`, `AUTO`.

###### `<relations>`

A list of relations for the entity.

- `entityName` (required): The name of the related entity.
- `relationName` (required): The name of the relation.
- `owningSide` (required): Whether this entity owns the relation (`true` or `false`).
- `relationType` (required): The type of the relation. Options are `ONE_TO_ONE`, `ONE_TO_MANY`, `MANY_TO_ONE`, `MANY_TO_MANY`.
- `fetchType` (optional): The fetch type for the relation. Options are `LAZY`, `EAGER`.

###### `<attributes>`

A list of attributes for the entity.

- `name` (required): The name of the attribute.
- `type` (required): The type of the attribute. Options include `LONG`, `STRING`, `BOOLEAN`, `ENUM`, etc.
- `enumName` (required if type is `ENUM`): The name of the enum this attribute refers to.

##### `<enums>`

A list of enums in the application.

###### `<enum>`

Defines an enum.

- `name` (required): The name of the enum.
- `<value>` (required): Each value in the enum.



## Requirements

- Java Development Kit (JDK) 11 or higher
- Apache Maven or Gradle (for building the generated Spring Boot application)

## Contributing

Contributions to the Generator Spring Boot Application are welcome! If you encounter any issues or have ideas for improvements, please open an issue or submit a pull request on GitHub.

## License

The Generator Spring Boot Application is open-source software licensed under the [MIT License](LICENSE).
