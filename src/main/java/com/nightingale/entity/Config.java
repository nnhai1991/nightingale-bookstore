package com.nightingale.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hai
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Config implements Serializable {

	private static final long serialVersionUID = -3499634801349184570L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String code;
	
	@NotNull
	private String value;
	private String description;
	private String updatedBy;
	private Timestamp updatedDate;


	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

	@PrePersist
	protected void onCreate() {
		this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
	}

}
