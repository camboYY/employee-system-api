package com.employeesystemapi.controller;

import com.employeesystemapi.config.JwtUtil;
import com.employeesystemapi.model.AuthRequestModel;
import com.employeesystemapi.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private  AuthenticationManager authenticationManager;
    @Autowired
    private  CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthRequestModel authRequestModel) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestModel.getEmail(), authRequestModel.getPassword()));
        final UserDetails userDetails = userDetailsService.getUserByEmail(authRequestModel.getEmail());
        if(userDetails != null ) {
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
        }
        return ResponseEntity.status(400).body("Bad Request");
    }
}
