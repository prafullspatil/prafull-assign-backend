package com.mb.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.mb.model.Auditable;

@Entity
@Table(name = "products")
public class Product extends Auditable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "message")
	private String message;

	@Column(columnDefinition = "TEXT", name = "description")
	private String description;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private Integer price;

	@OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "p_fk", referencedColumnName = "id")
	private List<Image> images;

	@Column(name = "code")
	private String code;

	public long getId()
	{
		return id;
	}

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

	public void setId(long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public void setImages(List<Image> images)
	{
		this.images = images;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
