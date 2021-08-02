package com.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.model.DataBaseFile;

public interface DataBaseFileRepository extends JpaRepository<DataBaseFile, String> {
	List<DataBaseFile> findByFileName(String fileName);
}
