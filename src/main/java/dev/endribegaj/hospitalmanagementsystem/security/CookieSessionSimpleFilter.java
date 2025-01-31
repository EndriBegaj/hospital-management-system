package dev.endribegaj.hospitalmanagementsystem.security;


import dev.endribegaj.hospitalmanagementsystem.dtos.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
@Configuration
public class CookieSessionSimpleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Allow asset requests
        if (request.getRequestURI().contains("/assets")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Allowed paths that do not require authentication
        Set<String> allowedPaths = Set.of("/", "/page/create");

        if (allowedPaths.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;  // Return to stop further execution
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
                response.sendRedirect("/");
                return;
            }

            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Redirect to login if no valid session exists
        response.sendRedirect("/login?returnUrl=" + request.getRequestURI());
    }
}

