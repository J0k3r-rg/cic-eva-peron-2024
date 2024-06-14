package com.j0k3r_dev.cic_eva_peron.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
public class JwtService {

    @Autowired
    private Environment environment;

    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("SECRET_KEY_TOKEN_LOGIN")));
        return JWT.create()
                .withIssuer("MDS-SC")
                .withSubject(username)
                .withExpiresAt(getDateExpiration())
                .sign(algorithm);
    }

    private Instant getDateExpiration(){
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }


    public String validateAndGetSubject(String token) throws JWTDecodeException {
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("SECRET_KEY_TOKEN_LOGIN")));
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("MDS-SC")
                .build();
        return verifier.verify(token).getSubject();
    }

    public String getTokenAuthorization(String authorization){
        return authorization.replace("Bearer ","");
    }

    public String getAuthorization(HttpServletRequest request){
        return  request.getHeader("Authorization");
    }
}
