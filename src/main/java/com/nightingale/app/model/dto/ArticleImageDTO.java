package com.nightingale.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nightingale.app.model.constraints.RequiredImage;

import lombok.Data;

/**
 * @author hai
 *
 */
@Data
public class ArticleImageDTO implements Serializable {

	private static final long serialVersionUID = -4951262262794080160L;
	
	@NotNull
	private Integer sequence = 0;
	
	@NotNull
	private Integer articleId;

	@RequiredImage
	private MultipartFile image;
	
	private Integer articleImageId;	
}
