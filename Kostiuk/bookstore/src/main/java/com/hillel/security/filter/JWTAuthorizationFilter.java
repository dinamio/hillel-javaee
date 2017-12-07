package com.hillel.security.filter;

import com.hillel.model.User;
import com.hillel.security.AuthHelper;
import com.hillel.security.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthHelper authHelper;

    public JWTAuthorizationFilter(AuthHelper authHelper, AuthenticationManager authManager) {
        super(authManager);
        this.authHelper = authHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        UserAuthentication authentication = authHelper.checkTokenAndBuildAuthentication(req).orElse(null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication != null) {
            Date expirationDate = authentication.getDetails().getExpirationDate();
            String expDateForm = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(expirationDate);
            req.setAttribute("expiration_date", expDateForm);
            User principal = authentication.getPrincipal();
            req.setAttribute("firstName", principal.getFirstName());
            req.setAttribute("lastName", principal.getLastName());
            req.setAttribute("login", principal.getLoginName());
        }
        chain.doFilter(req, res);
    }

}
