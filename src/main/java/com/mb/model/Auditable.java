package com.mb.model;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable
{

	@CreatedBy
	protected String createdBy;

	@CreatedDate
	protected Date createdDate;

	@LastModifiedBy
	protected String lastModifiedBy;

	@LastModifiedDate
	protected Date lastModifiedDate;

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public String getLastModifiedBy()
	{
		return lastModifiedBy;
	}

	public Date getLastModifiedDate()
	{
		return lastModifiedDate;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setLastModifiedBy(String lastModifiedBy)
	{
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setLastModifiedDate(Date lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

}
