package com.gft.loja.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.loja.api.model.LoginForm;
import com.gft.loja.api.model.TokenModel;
import com.gft.loja.domain.security.TokenService;


@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired 
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenModel> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenModel(token,"Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}

