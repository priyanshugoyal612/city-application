package com.sg.citylistapp.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper helper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// a)Get Jwt token from request , format //Will start from Bearer
		String requestToken = request.getHeader("Authorization");
		System.out.println("Request Token " + requestToken);

		String username = null;
		String token = null;
		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = helper.getUsernameFromToken(token);
			} catch (IllegalArgumentException exp) {
				System.out.println("Unable to get Jwt Token");

			} catch (ExpiredJwtException e) {
				System.out.println("JWT token Expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid JWT Exceptions");
			}

		} else {
			System.out.println("JWT token is not start with bearer or null");
		}

		// b) Validate Token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (helper.validateToken(token, userDetails)) {
				// authenticate
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Invalidate JWT Token");
			}
		} else {
			System.out.println("Username is null or security context is null");
		}
		
		filterChain.doFilter(request, response);

		// c) Get User from token
		// d)Load user detail associated with token
		// e)Set Spring Security(Authentication setif validates)

	}
}
