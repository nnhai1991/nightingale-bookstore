package com.nightingale.app.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hai
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)

public class ArticleImage extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7330242016333653993L;
	
	private Integer sequence = 0;

	@NotNull
	private Integer articleId;

	@NotNull
	private Integer assetId;
}
