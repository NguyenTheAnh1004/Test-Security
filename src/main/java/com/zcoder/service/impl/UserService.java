package com.zcoder.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zcoder.dto.UserDTO;
import com.zcoder.entity.RoleEntity;
import com.zcoder.entity.UserEntity;
import com.zcoder.repository.RoleRepository;
import com.zcoder.repository.UserRepository;
import com.zcoder.service.IUserService;
@Service
public class UserService implements IUserService {
	@Autowired
    PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDTO findByUsername(String name) {
		UserEntity entity = new UserEntity();
		entity = userRepository.findOneByUsername(name);
		modelMapper.typeMap(UserEntity.class, UserDTO.class).addMappings(mapper -> mapper.skip(UserDTO::setRoles));
		modelMapper.typeMap(UserEntity.class, UserDTO.class).addMappings(mapper -> mapper.skip(UserDTO::setPassWord));
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		entity.getRoles().forEach(item -> {
			dto.getRoles().add(item.getName());  
		});
		return dto;
	}

	@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}

	@Override
	public UserDTO save(UserDTO dto) {
		UserEntity entity = new UserEntity();
		modelMapper.typeMap(UserDTO.class, UserEntity.class).addMappings(mapper -> mapper.skip(UserEntity::setRoles));
		entity = modelMapper.map(dto, UserEntity.class);   

		for(String items: dto.getRoles()) {
			RoleEntity roles = roleRepository.findOneByName(items);
			entity.getRoles().add(roles);
		}
		entity.setPassword(encoder.encode(entity.getPassword()));
		entity = userRepository.save(entity);
		modelMapper.typeMap(UserEntity.class, UserDTO.class).addMappings(mapper -> mapper.skip(UserDTO::setRoles));
		UserDTO result = new UserDTO();
		UserDTO results = modelMapper.map(entity, UserDTO.class);
		entity.getRoles().forEach(items -> {
			results.getRoles().add(items.getName());  
		});
        return results;
	}

}
