package com.zcoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcoder.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Boolean existsByUsername(String username);

	UserEntity findOneByUsername(String name);
	
	Optional<UserEntity> findByUsername(String username);

}
