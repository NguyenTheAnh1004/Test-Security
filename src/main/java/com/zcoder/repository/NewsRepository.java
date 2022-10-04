package com.zcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcoder.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

	NewsEntity findOneById(Long id);

}
