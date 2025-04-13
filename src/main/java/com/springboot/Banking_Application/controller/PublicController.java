package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.elastic.*;
import com.springboot.Banking_Application.service.UserService;
import com.springboot.Banking_Application.service.impl.ElasticUserService;
import com.springboot.Banking_Application.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final ElasticUserService elasticUserService;

    public PublicController(AuthenticationManager authenticationManager,
                            UserService userService,
                            UserDetailsService userDetailsService,
                            JwtUtil jwtUtil, ElasticUserService elasticUserService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.elasticUserService = elasticUserService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp (@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        ElasticUser elasticUser = ElasticUserMapper.mapToElasticUser(createdUserDto);
        elasticUserService.save(elasticUser);
        return ResponseEntity.ok(createdUserDto);
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getUserName(), userDto.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserName());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("USER");

            return ResponseEntity.ok(Map.of("token", jwtToken, "role", role));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Incorrect username or password");
        }
    }
}