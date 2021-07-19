package com.insurancepolicy.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.insurancepolicy.exception.NotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtUtil {

	Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	public static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	public static final byte[] secretBytes = secret.getEncoded();
	public static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public static String jwtTokenGenerator(String secretKey, int id) {

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(id)).setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes);

		String token = builder.compact();

		return token;
	}

	public static int tokenVerification(String token) throws NotFoundException{
		// This line will throw an exception if it is not a signed JWS (as expected)
		if (StringUtils.isEmpty(token)) {
			throw new NotFoundException("Token is empty");
		}
		
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}

}
