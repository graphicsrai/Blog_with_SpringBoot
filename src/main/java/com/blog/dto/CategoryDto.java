package com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int catId;
	@NotBlank
	@Size(min=4,message="Descreption should be min 4 char")
	private String catTitle;
	@NotBlank
	@Size(min=10,message="Descreption should be min 10 char")
	private String catDesc;
}
