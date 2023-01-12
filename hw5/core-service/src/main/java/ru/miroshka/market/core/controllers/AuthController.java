package ru.miroshka.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.miroshka.market.core.dto.JwtRequest;
import ru.miroshka.market.core.dto.JwtResponse;
import ru.miroshka.market.core.dto.StringResponse;
import ru.miroshka.market.core.servicies.JwtService;
import ru.miroshka.market.core.servicies.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException exp) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtService.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/secured")
    public String helloSecurity() {
        return "Hello";
    }

    @GetMapping("/check_auth")
    public StringResponse checkAuth(Principal principal) {

        return new StringResponse(principal.getName());
    }

}
