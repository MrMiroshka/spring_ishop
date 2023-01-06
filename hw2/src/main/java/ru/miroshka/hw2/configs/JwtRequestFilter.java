package ru.miroshka.hw2.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.miroshka.hw2.servicies.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (authHeader!= null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            username = jwtService.getUsernameFromeToken(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null,jwtService.getRoles(jwt).stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request,response);
    }
}
