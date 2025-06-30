package com.backend.ToDoList.config;

import com.backend.ToDoList.service.UserService;
import com.backend.ToDoList.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if(jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            if(jwtUtils.validateToken(jwt)) {
                int id = jwtUtils.getUserIdFormToken(jwt);
                UserDetails u =  userDetailsService.loadUserByUsername(String.valueOf(id));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                        (u, null, u.getAuthorities());
            }
        }
    }
}
