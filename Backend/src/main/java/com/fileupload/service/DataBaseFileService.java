package com.fileupload.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.exception.FileNotFoundException;
import com.fileupload.exception.FileStorageException;
import com.fileupload.model.DataBaseFile;
import com.fileupload.repository.DataBaseFileRepository;

@Service
public class DataBaseFileService {

	@Autowired
	DataBaseFileRepository dbFileRepository;
	
	public DataBaseFile store(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			InputStream in = new ByteArrayInputStream(file.getBytes());
			BufferedImage image = ImageIO.read(in);
			if (image.getHeight() > 512 || image.getWidth() > 512) {
				throw new FileStorageException("Sorry! Your image's width or height over 512ppx");
			}
			
			if (!(fileType == "jpg" || fileType=="jpeg" || fileType=="png"|| fileType=="bmp")) {
				throw new FileStorageException("Sorry! Your image type should be in jpg, png, jpeg, bmp");
			}
			
			DataBaseFile dbFile = new DataBaseFile(fileName, file.getContentType(), file.getBytes());
			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	public List<DataBaseFile> getFile(String fileName) {
		List<DataBaseFile> dbFiles = dbFileRepository.findByFileName(fileName);
		if (dbFiles.isEmpty()) {
			throw new FileNotFoundException("Could not find file with name " + fileName);
		} else {
			return dbFiles;
		}
				
		
	}
}
