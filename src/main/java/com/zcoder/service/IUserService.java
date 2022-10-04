package com.zcoder.service;

import java.util.Optional;

import com.zcoder.dto.UserDTO;
import com.zcoder.entity.UserEntity;

public interface IUserService {
	UserDTO findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    UserDTO save(UserDTO user);
}
