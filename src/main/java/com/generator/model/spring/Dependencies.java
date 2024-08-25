package com.generator.model.spring;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Dependencies {
	public String href;
    public boolean templated;
    public String type;
    public ArrayList<Value> values;
}
