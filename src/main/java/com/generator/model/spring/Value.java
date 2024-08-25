package com.generator.model.spring;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Value {
	public String name;
    public ArrayList<Value> values;
    public String id;
    public String description;
    public Links _links;
    public String versionRange;
    public String action;
    public Tags tags;
}
