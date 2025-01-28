package dev.endribegaj.hospitalmanagementsystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminRoleFilter {
    public static void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] paths = {"/admin", "/doctors", "/patients"}; // lista e page/paths qe ka drejt me vizitu, pastaj me nje foreach me i vizitu

        if(request.getRequestURI().contains("/admin")) {
            filterChain.doFilter(request, response);
            return;
        }
        response.sendRedirect("/error403"); // Unauthorized
    }
}
