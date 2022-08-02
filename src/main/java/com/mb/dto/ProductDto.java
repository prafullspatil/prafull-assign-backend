package com.mb.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.mb.entity.Image;

public class ProductDto
{

	@NotEmpty
	private String name;

	@NotEmpty
	private String message;

	@NotEmpty
	private String description;

	@NotNull
	private Integer quantity;

	@NotNull
	private Integer price;

	@NotEmpty
	private List<Image> images;

	@NotEmpty
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
