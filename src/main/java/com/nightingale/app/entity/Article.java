package com.nightingale.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hai
 *
 */
@Entity
public class Article implements Serializable {

	private static final long serialVersionUID = 4291701426443797243L;
	@Id
	private Integer id;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String code;
	
	private String description;

	private BigDecimal price;

	private BigDecimal reservationCost;

	private Integer assetId;

	@NotNull
	private Boolean enabled;

	//@NotNull
	//@Min(1)
	private Integer sequence;
	
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelCode() {
		return code;
	}

	public void setModelCode(String modelCode) {
		this.code = modelCode;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getReservationCost() {
		return reservationCost;
	}

	public void setReservationCost(BigDecimal reservationCost) {
		this.reservationCost = reservationCost;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
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
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public LocalDateTime getCreatedDateLocal() {
		return createdDate.toLocalDateTime();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
