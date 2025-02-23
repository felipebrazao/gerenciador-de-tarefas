package com.praticing.tarefas.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class AuthController {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/authenticate")
	    public String createToken(@RequestParam String username) {
	        return jwtUtil.generateToken(username);
	    }
	}

