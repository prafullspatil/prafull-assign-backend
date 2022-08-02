package com.mb.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.mb.entity.Image;

public class ProductDto
{

	@NotBlank
	private String name;

	@NotBlank
	private String message;

	@NotBlank
	private String description;

	@NotNull
	private Integer quantity;

	@NotNull
	private Integer price;

	@NotEmpty
	private List<Image> images;

	@NotBlank
	private String code;

	public String getName()
	{
		return name;
	}

	public String getMessage()
	{
		return message;
	}

	public String getDescription()
	{
		return description;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public Integer getPrice()
	{
		return price;
	}

	public List<Image> getImages()
	{
		return images;
	}

	public String getCode()
	{
		return code;
	}

}
