package com.hillel.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hillel.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class AuthHelper {

    @Getter
    @Setter
    private static User currentUser;
    private static byte[] secret = SecureRandom.getSeed(1024);
    private static String issuer = "com.hillel.kostiuk";

    public static Optional<String> generateToken(String login, TemporalAmount time) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        LocalDateTime localDate = LocalDateTime.now().plus(time);
        Date expirationDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return Optional.ofNullable(JWT.create()
                .withIssuer(issuer)
                .withClaim("login", login)
                .withExpiresAt(expirationDate)
                .sign(algorithm));
    }

    public static Optional<DecodedJWT> verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        try {
            DecodedJWT result = verifier.verify(token);
            return Optional.of(result);
        } catch (JWTVerificationException e) {
            log.warn("Token is not valid, {}", LocalDateTime.now());
//            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
