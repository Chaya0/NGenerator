package com.generator.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "app")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppModel {
	@XmlElementWrapper(name = "entities")
	@XmlElement(name = "entity")
	private List<Entity> entities;
	@XmlElementWrapper(name = "enums")
	@XmlElement(name = "enum")
	private List<EnumModel> enums;

	public AppModel() {
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<EnumModel> getEnums() {
		return enums;
	}

	public Entity getEntityByName(String entityName) {
		for (Entity entity : entities) {
			if (entity.getName().equals(entityName)) return entity;
		}
		return null;
	}

	@Override
	public String toString() {
		return "AppModel [entities=" + entities + ", enums=" + enums + "]";
	}
}
