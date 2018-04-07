package com.nightingale.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
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
public class Stock extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6960439458185071526L;

	@ManyToOne
	private Article article;
	
	@ManyToOne
	private Site fromSite;
	
	@ManyToOne
	private Site toSite;
		
	private int quantity;
	
	private String type;
	
	private Timestamp stockDateUTC;
	
	@Transient
	@DateTimeFormat(pattern=DateFormat.DISPLAY_DATE_TIME)
	private LocalDateTime stockDateLocal;
	
	public void convertToLocalTime() {
		try {
			CustomUserDetails customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (this.stockDateLocal == null ) {
				this.setStockDateLocal(UtilDates.getLocalDateFromUTC(this.getStockDateUTC().toLocalDateTime(),
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
			if (this.stockDateUTC == null) {
				this.setStockDateUTC(Timestamp
						.valueOf(UtilDates.getUTCFromTimeZone(this.getStockDateLocal(), customUser.getUser().getTimezone())));
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
