package com.hillel.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hillel.model.User;
import com.hillel.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
public class AuthHelper {

    public final static String TOKEN_AUTH_NAME = "X-AUTH-TOKEN";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security-token-lifetime-minutes}")
    private String lifetimeMinutes;

    private byte[] secret;
    private String issuer = "com.hillel.kostiuk";


    @PostConstruct
    private void init() {
        try {
            secret = Files.readAllBytes(Paths.get(Thread.currentThread()
                    .getContextClassLoader()
                    .getResource("crypto/secret.key").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Optional<UserAuthentication> getLoginAuthentication(HttpServletRequest request) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            LocalDateTime localDate = LocalDateTime.now().plus(Duration.ofMinutes(Long.parseLong(lifetimeMinutes)));
            Date expirationDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

            User user = userService.getByLogin(login).orElse(null);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                AuthDetails authDetails = new AuthDetails(expirationDate);
                return Optional.of(new UserAuthentication(user, authDetails));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<String> generateToken(@NonNull UserAuthentication userAuthentication) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return Optional.ofNullable(JWT.create()
                .withIssuer(issuer)
                .withJWTId(userAuthentication.getPrincipal().getId().toString())
                .withClaim("login", userAuthentication.getName())
                .withExpiresAt(userAuthentication.getDetails().getExpirationDate())
                .sign(algorithm));
    }

    public Optional<UserAuthentication> checkTokenAndBuildAuthentication(HttpServletRequest request) {
        return Stream.of(request.getCookies()).filter(cookie -> cookie.getName().equals(TOKEN_AUTH_NAME)).findAny()
                .map(Cookie::getValue)
                .flatMap(this::buildAuthentication);
    }

    private Optional<UserAuthentication> buildAuthentication(String token) {
        DecodedJWT jwt = verifyToken(token).orElse(null);
        if (jwt != null) {
            Date expDate = jwt.getExpiresAt();
            long userID = Long.parseLong(jwt.getId());
            AuthDetails authDetails = new AuthDetails();
            authDetails.setExpirationDate(expDate);
            User user = userService.getById(userID).orElse(null);
            if (user != null) {
                return Optional.of(new UserAuthentication(user, authDetails));
            }
        }
        return Optional.empty();
    }


    private Optional<DecodedJWT> verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        try {
            DecodedJWT result = verifier.verify(token);
            return Optional.of(result);
        } catch (JWTVerificationException e) {
            log.warn("Token is not valid, {}", LocalDateTime.now());
            return Optional.empty();
        }
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
