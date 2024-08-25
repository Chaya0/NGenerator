package com.generator.model.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseType {
	private String type;
    @JsonProperty("default") 
    private String mydefault;
}
