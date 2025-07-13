package com.foxdev.project.findJobProject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxdev.project.findJobProject.domain.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.delegate.commence(request, response, authException);
        response.setContentType("application/json; charset=utf-8");

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        String errorMessage  = Optional.ofNullable(authException.getCause())
                        .map(Throwable::getMessage)
                        .orElse(authException.getMessage());
        apiResponse.setError(errorMessage);
        apiResponse.setMessage("Unauthorized");
        apiResponse.setTimestamp(LocalDateTime.now());

        mapper.writeValue(response.getWriter(), apiResponse);
    }
}
