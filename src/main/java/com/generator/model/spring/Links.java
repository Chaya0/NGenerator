package com.generator.model.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Links {
	@JsonProperty("gradle-project") 
	public Link gradleProject;
	@JsonProperty("gradle-project-kotlin") 
    public Link gradleProjectKotlin;
	@JsonProperty("gradle-build") 
    public Link gradleBuild;
	@JsonProperty("maven-project") 
    public Link mavenProject;
	@JsonProperty("maven-build") 
    public Link mavenBuild;
    public Dependencies dependencies;
    public Object reference;
    public Object guide;
    public Sample home;
    public Sample sample;
}
