package com.hias.apps.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hias.apps.util.ErrorResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		JSONObject resp = new JSONObject();
		resp.put("data", null);
		resp.put("success", false);
		resp.put("error", new ErrorResponse("UnAuthorized", 401));
		response.getOutputStream().println(objectMapper.writeValueAsString(resp));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

	}

}
