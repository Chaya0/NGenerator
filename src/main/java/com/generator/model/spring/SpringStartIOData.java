package com.generator.model.spring;

import lombok.Data;

@Data
public class SpringStartIOData {
	public Links _links;
    public Dependencies dependencies;
    public Type type;
    public ProjectAttribute packaging;
    public ProjectAttribute javaVersion;
    public ProjectAttribute language;
    public ProjectAttribute bootVersion;
    public BaseType groupId;
    public BaseType artifactId;
    public BaseType version;
    public BaseType name;
    public BaseType description;
    public BaseType packageName;
}
