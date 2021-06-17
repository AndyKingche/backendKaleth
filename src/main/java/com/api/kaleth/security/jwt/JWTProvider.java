package com.api.kaleth.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.api.kaleth.domain.UsUser;
import com.api.kaleth.security.domain.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTProvider {
	private final static Logger logger =  LoggerFactory.getLogger(JWTEntryPoint.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generatorToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal)authentication.getPrincipal();
		
		return Jwts.builder().setSubject(usuarioPrincipal.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+ expiration * 1000))
				.signWith(SignatureAlgorithm.HS512,secret)
				.compact();
	}
	
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch(MalformedJwtException e) {
			logger.error("Token mal formado");
		}catch(UnsupportedJwtException e) {
			logger.error("Token NO SOPORTADO");
		}catch(ExpiredJwtException e) {
			logger.error("Token EXPIRADO");
		}catch(IllegalArgumentException e) {
			logger.error("Token VACIO");
		}catch(SignatureException e) {
			logger.error("Fallo con la firma");
		}
		return false;
	}
}
