package com.zcoder.api;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zcoder.dto.JwtResponse;
import com.zcoder.dto.UserDTO;
import com.zcoder.security.jwt.JwtProvider;
import com.zcoder.security.jwt.JwtTokenFilter;
import com.zcoder.security.userprincal.UserPrinciple;
import com.zcoder.service.impl.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserAPI {
	@Autowired
	UserService userService;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private JwtTokenFilter authenticationFilter;
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
//    @RolesAllowed("admin")
    @PreAuthorize("hasRole('admin')")
	@GetMapping(value = "/User/Profile")
	public UserDTO profileToken(HttpServletRequest request) {
		String token = authenticationFilter.getJwt(request);
		String username = jwtProvider.getUerNameFromToken(token);
		return userService.findByUsername(username);
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping(value = "/User")
	public UserDTO createUser(@RequestBody UserDTO user) {
		return userService.save(user);
	}

	@PostMapping("/Signin")
	public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassWord()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.createToken(authentication);
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
//		return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName()));
	}
}
