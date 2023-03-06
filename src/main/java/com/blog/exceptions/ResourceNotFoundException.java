package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resoutceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s",resoutceName,fieldName,fieldValue));
		this.resoutceName = resoutceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	String resoutceName;
	String fieldName;
	long fieldValue;
	
	
}
