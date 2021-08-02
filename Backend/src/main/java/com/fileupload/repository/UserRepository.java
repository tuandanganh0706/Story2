package com.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
