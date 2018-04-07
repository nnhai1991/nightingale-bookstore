package com.nightingale.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nightingale.security.CustomUserDetails;
import com.nightingale.util.DateFormat;
import com.nightingale.util.UtilDates;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public class Discount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133457586537741132L;
	
	@ManyToMany
	private List<Article> articles;
	
	private String description;
	
	private int amount;
	
	private String type;
		
	private Timestamp startDateUTC;
	
	private Timestamp endDateUTC;
	
	private boolean enabled;
	
	@Transient
	@DateTimeFormat(pattern=DateFormat.DISPLAY_DATE_TIME)
	private LocalDateTime startDateLocal;

	@Transient
	@DateTimeFormat(pattern=DateFormat.DISPLAY_DATE_TIME)
	private LocalDateTime endDateLocal;

	public void convertToLocalTime() {
		try {
			CustomUserDetails customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (this.endDateLocal == null && this.startDateLocal == null) {
				this.setEndDateLocal(UtilDates.getLocalDateFromUTC(this.getEndDateUTC().toLocalDateTime(),
						customUser.getUser().getTimezone()));
				this.setStartDateLocal(UtilDates.getLocalDateFromUTC(this.getStartDateUTC().toLocalDateTime(),
						customUser.getUser().getTimezone()));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void convertToUTC() {
		try {
			CustomUserDetails customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (this.endDateUTC == null && this.endDateUTC == null) {
				this.setEndDateUTC(Timestamp
						.valueOf(UtilDates.getUTCFromTimeZone(this.getEndDateLocal(), customUser.getUser().getTimezone())));
				this.setStartDateUTC(Timestamp.valueOf(
						UtilDates.getUTCFromTimeZone(this.getStartDateLocal(), customUser.getUser().getTimezone())));			
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@PreUpdate
	protected void onUpdate() {
		super.onUpdate();
		convertToUTC();
	}

	@PrePersist
	protected void onCreate() {
		super.onUpdate();
		convertToUTC();
	}
}
