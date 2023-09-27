package com.generator.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "app")
public class AppModel {
	private List<Entity> entities;
	private SpringProperties springProperties;
	
	public AppModel(List<Entity> entities, SpringProperties springProperties) {
		super();
		this.entities = entities;
		this.springProperties = springProperties;
	}

	@XmlElementWrapper(name = "entities")
	@XmlElement(name = "entities")
	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	@XmlElement(name = "spring-properties")
	public SpringProperties getSpringProperties() {
		return springProperties;
	}

	public void setSpringProperties(SpringProperties springProperties) {
		this.springProperties = springProperties;
	}

	
	
}
