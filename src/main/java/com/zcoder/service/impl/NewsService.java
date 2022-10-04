package com.zcoder.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import com.zcoder.conf.CloudinaryConfig;
import com.zcoder.dto.NewsDTO;
import com.zcoder.entity.CategoryEntity;
import com.zcoder.entity.NewsEntity;
import com.zcoder.repository.CategoryRepository;
import com.zcoder.repository.NewsRepository;
import com.zcoder.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Value("${cloud.url}")
	private String cloudinaryURL;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private CloudinaryConfig cloundinary;

	@Autowired
	private NewsRepository newsRepository;

	private Slugify slg = new Slugify();

	@Autowired
	private CategoryRepository cateRepository;

	@Override
	public NewsDTO Save(String title, MultipartFile file, String shortDescription, String content, String categoryCode)
			throws IOException {

		// upload on cloundinary
		cloundinary.upload(file.getBytes(),
				ObjectUtils.asMap("public_id", FilenameUtils.removeExtension(file.getOriginalFilename()),
						"unique_filename", "false", "folder", "blogzcoder/"));
		// end upload
		NewsDTO newsDTO = new NewsDTO(title, file.getOriginalFilename(), shortDescription, content, categoryCode);
		newsDTO.setCode(slg.slugify(title));
		NewsEntity newsEntity = modelMapper.map(newsDTO, NewsEntity.class);
		CategoryEntity cateEntity = cateRepository.findOneByCode(categoryCode);
		newsEntity.setCategory(cateEntity);
		newsEntity = newsRepository.save(newsEntity);
		NewsDTO dto = modelMapper.map(newsEntity, NewsDTO.class);
		return dto;
	}

	@Override
	public NewsDTO Update(Long id, String title, MultipartFile file, String shortDescription, String content,
			String categoryCode) throws IOException {
		NewsDTO newsDTO = new NewsDTO(title, file.getOriginalFilename(), shortDescription, content, categoryCode);
		newsDTO.setCode(slg.slugify(title));
		newsDTO.setId(id);
		NewsEntity oldEntity = newsRepository.findOneById(id);
		newsDTO.setCreatedDate(oldEntity.getCreatedDate());
		// update image
		cloundinary.delete(
				"blogzcoder/" + FilenameUtils.removeExtension(oldEntity.getThumbnail().replaceAll(" ", "%20")),
				ObjectUtils.asMap("invalidate", true));

		cloundinary.upload(file.getBytes(),
				ObjectUtils.asMap("public_id", FilenameUtils.removeExtension(file.getOriginalFilename()),
						"unique_filename", "false", "folder", "blogzcoder/"));
		//
		oldEntity = modelMapper.map(newsDTO, NewsEntity.class);
		CategoryEntity cateEntity = cateRepository.findOneByCode(categoryCode);
		oldEntity.setCategory(cateEntity);
		oldEntity = newsRepository.save(oldEntity);
		NewsDTO dto = modelMapper.map(oldEntity, NewsDTO.class);
		return dto;
	}

	@Override
	public List<NewsDTO> findAll() {
		List<NewsEntity> newsEntity = new ArrayList<NewsEntity>();
		newsEntity = newsRepository.findAll();
		List<NewsDTO> newsDTO = new ArrayList<NewsDTO>();
		for (NewsEntity items : newsEntity) {
			NewsDTO dto = modelMapper.map(items, NewsDTO.class);
			newsDTO.add(dto);
			urlImage(dto);
		}
		return newsDTO;
	}

	public void urlImage(NewsDTO dto) {
		String fileDownloadUri = cloudinaryURL + dto.getThumbnail();
		String linkCloundinary = fileDownloadUri.replaceAll(" ", "%20");
		dto.setThumbnail(linkCloundinary);

	}

}
