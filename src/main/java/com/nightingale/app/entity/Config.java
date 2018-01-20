package com.nightingale.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author hai
 *
 */
@Entity
public class Config implements Serializable {

	private static final long serialVersionUID = -3499634801349184570L;
	@Id
	private Integer id;
	private String key;
	private String value;
	private String description;
	private String updatedBy;
	private Timestamp updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

	@PrePersist
	protected void onCreate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

}
