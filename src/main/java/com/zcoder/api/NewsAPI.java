package com.zcoder.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zcoder.dto.NewsDTO;
import com.zcoder.service.impl.NewsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NewsAPI {
	
	@Autowired
	NewsService newsService;
	
	@PreAuthorize("hasRole('admin')")
	@PostMapping("/News")
	public NewsDTO saveNews(@RequestParam("title") String title, @RequestParam("file") MultipartFile file,
			@RequestParam("shortDescription") String shortDescription,@RequestParam("content") String content,@RequestParam("categoryCode") String categoryCode ) throws IOException {
		return newsService.Save(title, file, shortDescription, content, categoryCode); 
	}
	@PreAuthorize("hasRole('user')")
	@GetMapping("/News")
	public List<NewsDTO> showNews() {
		return newsService.findAll();
	}
	
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/News")
	public NewsDTO updateNews(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("file") MultipartFile file,
			@RequestParam("shortDescription") String shortDescription,@RequestParam("content") String content,@RequestParam("categoryCode") String categoryCode ) throws IOException {
		return newsService.Update(id, title, file, shortDescription, content, categoryCode); 
	}
}
