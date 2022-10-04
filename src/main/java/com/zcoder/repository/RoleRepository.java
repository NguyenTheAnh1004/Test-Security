package com.zcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcoder.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findOneByName(String items);

}
