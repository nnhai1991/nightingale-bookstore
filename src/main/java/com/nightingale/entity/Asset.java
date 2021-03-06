package com.nightingale.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hai
 */
@Entity
@Data
@NoArgsConstructor
public class Asset implements Serializable {

    private static final long serialVersionUID = 8534855707534977027L;
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
    @NotNull
    private String mimeType;
    
    @NotNull
    private String displayName;
    
    @NotNull
    private String path;

}
