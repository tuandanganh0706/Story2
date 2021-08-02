package com.fileupload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fileupload.model.DataBaseFile;
import com.fileupload.service.DataBaseFileService;

@CrossOrigin
@RestController
public class FileDownloadController {

	@Autowired
	DataBaseFileService fileStorageService;
	
	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
		List<DataBaseFile> dbFiles = fileStorageService.getFile(fileName);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbFiles.get(0).getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFiles.get(0).getFileName() + "\"")
				.body(new ByteArrayResource(dbFiles.get(0).getData()));
	}
}
