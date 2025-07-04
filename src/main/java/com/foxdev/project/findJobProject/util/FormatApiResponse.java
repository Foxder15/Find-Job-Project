package com.foxdev.project.findJobProject.util;

import com.foxdev.project.findJobProject.domain.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FormatApiResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = httpServletResponse.getStatus();

        if (body instanceof String) {
            return body;
        }

        ApiResponse<Object> res = new ApiResponse<Object>();
        res.setStatus(status);

        if (status >= 400) {
            return body;
        } else {
            res.setData(body);
            res.setMessage("success");
        }

        res.setTimestamp(LocalDateTime.now());

        return res;
    }
}
