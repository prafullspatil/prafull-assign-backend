package com.mb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "url")
	private String url;

	public Image(long id, String url)
	{
		super();
		this.id = id;
		this.url = url;
	}

	public Image()
	{

	}

	public long getId()
	{
		return id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}
