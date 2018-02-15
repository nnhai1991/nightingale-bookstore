package com.nightingale.app.service.impl;

import com.nightingale.app.entity.Asset;
import com.nightingale.app.exception.ObjectCreationException;

import com.nightingale.app.repository.AssetRepository;
import com.nightingale.app.service.AssetService;
import com.nightingale.app.util.UtilFiles;
import com.nightingale.web.util.UtilValidation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Integer create(MultipartFile file, String baseDir) {

        if (UtilValidation.isFileNotEmpty(file))
            return create(file, baseDir, UtilFiles.getUniqueFilePath(file, baseDir));

        return 0;
    }

    /**
     *
     * @param file
     * @param baseDir
     * @param path
     * @return This is returning an Integer instead of a boolean because other services will use it as the ID immediately
     */
    @Override
    public Integer create(MultipartFile file, String baseDir, String path) {


        if (UtilValidation.isFileNotEmpty(file)) {
            Asset asset = fromMultipartFile(file, path);

            if (asset != null) {
                try {

                    File newFile = new File(baseDir + asset.getPath());
                    newFile.getParentFile()
                           .mkdirs();
                    file.transferTo(newFile);

                    return assetRepository.save(asset).getId();
                } catch (IllegalStateException | IOException | DataIntegrityViolationException e) {
                	throw new ObjectCreationException(e.getStackTrace(), "create Asset", "", "Failed to create Asset", asset);
                    
                }

            }
        }

        return 0;
    }

    @Override
    public Asset read(Integer assetId) {

        if (UtilValidation.isValidId(assetId))
                return assetRepository.findOne(assetId);
        return null;
    }

    @Override
    public boolean delete(Integer assetId, String baseDir) {

        Asset asset = read(assetId);

        if (asset != null)
            return delete(assetId, baseDir, asset.getPath());
        return false;
    }

    @Transactional
    @Override
    public boolean delete(Integer assetId, String baseDir, String path) {

        //boolean isDeletedFromDB = false;
        boolean isDeletedFromFileSystem = false;

        if (UtilValidation.isValidId(assetId)) {

            assetRepository.delete(assetId);
            File file = new File(baseDir + path);
            isDeletedFromFileSystem = file.delete();
        }
        return isDeletedFromFileSystem;

    }

    private Asset fromMultipartFile(MultipartFile file, String path) {

        if (UtilValidation.isFileNotEmpty(file)) {

            Asset asset = new Asset();
            asset.setDisplayName(FilenameUtils.getName(file.getOriginalFilename()));
            asset.setMimeType(file.getContentType());
            asset.setPath(path);
            return asset;
        }

        return null;
    }

}
