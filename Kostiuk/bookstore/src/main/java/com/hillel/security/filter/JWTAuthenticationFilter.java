package com.hillel.security.filter;

import com.hillel.security.AuthHelper;
import com.hillel.security.UserAuthentication;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthHelper authHelper;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthHelper authHelper, AuthenticationManager authenticationManager) {
        this.authHelper = authHelper;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        return authHelper.getLoginAuthentication(req).orElseThrow(() -> new BadCredentialsException("Enter valid credentials!"));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Optional.ofNullable(auth)
                .map(authentication -> (UserAuthentication) authentication)
                .flatMap(authHelper::generateToken)
                .ifPresent(token -> res.addCookie(new Cookie(AuthHelper.TOKEN_AUTH_NAME, token)));
        chain.doFilter(req, res);
    }
}
