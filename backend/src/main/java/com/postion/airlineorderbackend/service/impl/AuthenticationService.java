package com.postion.airlineorderbackend.service.impl;

import com.postion.airlineorderbackend.dto.AuthRequest;
import com.postion.airlineorderbackend.dto.AuthResponse;
import com.postion.airlineorderbackend.model.User;
import com.postion.airlineorderbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepo userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtServiceImpl jwtUtil;
	
	private final AuthenticationManager authenticationManager;

	public AuthResponse authenticate(AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

		if (!userOpt.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		User user = userOpt.get();
				UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles("USER").build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return AuthResponse.builder().username(request.getUsername()).token(jwtToken).build();
	}

//	@Transactional
//	public AuthResponse register(RegisterUserInfoDto registerUserInfo) {
//		User user = User.builder().username(registerUserInfo.getUsername())
//				.passwordHash(passwordEncoder.encode(registerUserInfo.getPassword())).email(registerUserInfo.getEmail())
//				.firstName(registerUserInfo.getFirstName()).lastName(registerUserInfo.getLastName()).build();
//
//		userRepository.save(user);
//
//		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
//				.password(user.getPasswordHash()).roles("USER").build();
//
//		String jwtToken = jwtUtil.generateToken(userDetails);
//		return AuthResponse.builder().token(jwtToken).build();
//	}
}