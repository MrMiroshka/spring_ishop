package ru.miroshka.hw2.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Long jwtLifeTime;

}
