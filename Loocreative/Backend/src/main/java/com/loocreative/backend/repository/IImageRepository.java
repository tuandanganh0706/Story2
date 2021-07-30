package com.loocreative.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loocreative.backend.model.CImage;

@Repository
public interface IImageRepository extends JpaRepository<CImage, String> {

}
