package com.hias.apps.security;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public class JwtTokenFilter extends OncePerRequestFilter {
//GenericFilterBean{

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//	        throws IOException, ServletException {
//		String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(req, res);

//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith("Bearer ")) {
			authToken = header.replace("Bearer ", "");
			try {
				username = jwtTokenProvider.getUsername(authToken);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				Claims resp = e.getClaims();
				List<String> roleList = (List<String>) resp.get("roles");
				username = (String) resp.get("sub");
				authToken = jwtTokenProvider.createToken(username, roleList);
				request.setAttribute("Authorization", "Bearer " + authToken);
				SecurityContextHolder.getContext().setAuthentication(null);
				logger.warn("the token is expired and not valid anymore", e);
			}
		} else {
//	            logger.warn("couldn't find bearer string, will ignore the header");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenProvider.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(authToken,
						SecurityContextHolder.getContext().getAuthentication(), userDetails);
//	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}

}
