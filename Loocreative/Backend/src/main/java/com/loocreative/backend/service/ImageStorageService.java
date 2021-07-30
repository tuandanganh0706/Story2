package com.loocreative.backend.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.loocreative.backend.model.CImage;
import com.loocreative.backend.repository.IImageRepository;

@Component
public class ImageStorageService {

	@Autowired
	  private IImageRepository pImageRepo;

	  public CImage store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    CImage imageDB = new CImage(fileName, file.getContentType(), file.getBytes());

	    return pImageRepo.save(imageDB);
	  }

	  public CImage getImage(String id) {
	    return pImageRepo.findById(id).get();
	  }
	  
	  public Stream<CImage> getAllFiles() {
	    return pImageRepo.findAll().stream();
	  }
}
