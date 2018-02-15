package com.nightingale.web.controller;

import com.nightingale.app.entity.Asset;
import com.nightingale.app.service.AssetService;
import com.nightingale.web.util.UtilValidation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/public")
public class AssetController {

	@Value("${asset.upload.path}")
	private String uploadpath;

	private AssetService assetService;

	@Autowired
	public AssetController(AssetService assetService) {
		this.assetService = assetService;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<byte[]> getAsset(Model model, @PathVariable Integer id) throws IOException {

		if (UtilValidation.isValidId(id)) {

			byte[] assetByteArray = new byte[0];

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);

			Asset asset = assetService.read(id);
			if (asset != null) {
				headers.setContentType(MediaType.valueOf(asset.getMimeType()));

				// Because IE is confused, we have to set the content type
				// manually here based on the file extension instead of
				// using
				// produces = image/jpeg, image/png in the @Getmapping

				File file = new File(uploadpath + asset.getPath());
				if (file.exists()) {

					headers.setPragma("public");
					headers.setCacheControl("max-age=360000");
					headers.setExpires(360000);

					assetByteArray = IOUtils.toByteArray(new FileInputStream(file));

				}
			}
			return new ResponseEntity<byte[]>(assetByteArray, headers, HttpStatus.OK);
		}

		return null;
	}

}
