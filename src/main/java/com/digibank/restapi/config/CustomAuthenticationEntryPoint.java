package com.digibank.restapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorDetails.put("message", "Token Invalid");

        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, errorDetails);
        out.flush();
    }
}