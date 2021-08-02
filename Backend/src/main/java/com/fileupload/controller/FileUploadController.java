package com.fileupload.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileupload.exception.FileStorageException;
import com.fileupload.model.DataBaseFile;
import com.fileupload.payload.Response;
import com.fileupload.repository.DataBaseFileRepository;
import com.fileupload.service.DataBaseFileService;

@CrossOrigin
@RestController
public class FileUploadController {

	@Autowired
	DataBaseFileService fileStorageService;
	
	@Autowired
	DataBaseFileRepository fileRepository;
	
	@PostMapping("uploadFile")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		try {
			if (fileName.contains("..")) {
				return ResponseEntity.unprocessableEntity().body("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			InputStream in = new ByteArrayInputStream(file.getBytes());
			BufferedImage image = ImageIO.read(in);
			if (image.getHeight() > 512 || image.getWidth() > 512) {
				return ResponseEntity.unprocessableEntity().body("Sorry! Your image's width or height over 512ppx");
			}
			
			if (fileType!="jpg" && fileType!="jpeg" && fileType!="png" && fileType=="bmp") {
				return ResponseEntity.unprocessableEntity().body("Sorry! Your image type should be in jpg, png, jpeg, bmp");
			}
			
			DataBaseFile dbFile = new DataBaseFile(fileName, file.getContentType(), file.getBytes());
			
			String fileDownloadUri  = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadFile/")
					.path(dbFile.getFileName())
					.toUriString();
			
			Response response = new Response(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (IOException ex) {
			return ResponseEntity.unprocessableEntity().body("Could not store file " + fileName + ". Please try again!");
		}
	}
	
//	@PostMapping("/uploadFile")
//	public Response uploadFile(@RequestParam("file") MultipartFile file) {
//		DataBaseFile fileName = fileStorageService.store(file);
//		
//		String fileDownloadUri  = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/downloadFile/")
//				.path(fileName.getFileName())
//				.toUriString();
//		
//		return new Response(fileName.getId(),fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
//	}
	
	@PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity<Object>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
	
}
