package com.zcoder.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zcoder.dto.NewsDTO;

public interface INewsService {
	NewsDTO Save(String title, MultipartFile file, String shortDescription, String content, String categoryCode) throws IOException;
	NewsDTO Update(Long id, String title, MultipartFile file, String shortDescription, String content, String categoryCode) throws IOException;
	List<NewsDTO> findAll();
}
