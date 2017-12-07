package spring.config;

import database.dao.UserDao;
import database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static utils.Encryption.cryptWithMD5;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(username == null || password == null ){
            throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
        }
        User user = userDao.findOne(username);
        if(user != null){
            if(user.getPassHex().equals(cryptWithMD5(password))){
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));

    }

}
