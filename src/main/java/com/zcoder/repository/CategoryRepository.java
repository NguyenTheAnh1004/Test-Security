package com.zcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcoder.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findOneByCode(String categoryCode);

}
