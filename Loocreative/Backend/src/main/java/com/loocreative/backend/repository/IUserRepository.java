package com.loocreative.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loocreative.backend.model.CUsers;

@Repository
public interface IUserRepository extends JpaRepository<CUsers, Long>{

}
