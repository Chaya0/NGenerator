package com.generator.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "app")
public class AppModel {
	@XmlElementWrapper(name = "entities")
	@XmlElement(name = "entitie")
	private List<Entity> entities;
	@XmlElementWrapper(name = "enums")
	@XmlElement(name = "enum")
	private List<EnumModel> enums;

	public AppModel(List<Entity> entities) {
		super();
		this.entities = entities;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public Entity getEntityByName(String entityName) {
		for(Entity entity : entities) {
			if(entity.getName().equals(entityName)) return entity;
		}
		return null;
	}

}
