package com.student_management_system.project.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.student_management_system.project.backend.dtos.UserDto;
import com.student_management_system.project.backend.entites.Token;
import com.student_management_system.project.backend.exceptions.AppException;
import com.student_management_system.project.backend.repositories.TokenRepository;
import com.student_management_system.project.backend.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final TokenRepository tokenRepository;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String authToken = JWT.create()
                .withSubject(user.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .withClaim("role", user.getRole())
                .withClaim("userId", user.getId())
                .sign(algorithm);

        Token token = new Token();
        token.setEmail(user.getLogin());
        token.setIsValid(true);
        token.setTokenValue(authToken);
        token.setCreateTime(now.toInstant());
        token.setExpireTime(validity.toInstant());

        tokenRepository.save(token);

        return authToken;
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .login(decoded.getSubject())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .role(decoded.getClaim("role").asString())
                .build();
        
        Optional<Token> tokenOptional = tokenRepository.findByEmail(decoded.getSubject());
        if(!tokenOptional.isPresent() || (tokenOptional.isPresent() && !tokenOptional.get().getIsValid()) || !tokenOptional.get().getTokenValue().equals(token)) {
            throw new AppException("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", decoded.getClaim("role").asString())));

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

}
