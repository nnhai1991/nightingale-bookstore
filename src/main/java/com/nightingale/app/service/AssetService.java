package com.nightingale.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.nightingale.app.entity.Asset;


public interface AssetService {

	Integer create(MultipartFile file, String baseDir);
	Integer create(MultipartFile file, String baseDir, String path);
	Asset read(Integer assetId) ;
	boolean delete(Integer assetId, String baseDir);
	boolean delete(Integer assetId, String baseDir, String path);
}
