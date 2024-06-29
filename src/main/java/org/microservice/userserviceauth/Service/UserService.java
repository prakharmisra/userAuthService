package org.microservice.userserviceauth.Service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.microservice.userserviceauth.Models.Token;
import org.microservice.userserviceauth.Models.User;
import org.microservice.userserviceauth.Repositry.TokenRepositry;
import org.microservice.userserviceauth.Repositry.UserRepositry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;


@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private TokenRepositry tokenRepositry;

    private UserRepositry userRepositry;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder , UserRepositry userRepositry , TokenRepositry tokenRepositry){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepositry = userRepositry;
        this.tokenRepositry = tokenRepositry;
    }

    public User signUp(String email , String name ,  String password){

        User user = new User();
        user.setEmail(email);
        user.setName(name);

        String hashPassword = bCryptPasswordEncoder.encode(password);

        user.setHashpassword(hashPassword);

        return userRepositry.save(user);

    }
    public Token login(String email , String password){
        Optional<User> user = userRepositry.findByEmail(email);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found for email  " +  email);
        }

        if(!bCryptPasswordEncoder.matches(password, user.get().getHashpassword())){

            throw new RuntimeException("Password not matching");
        }

        Token token = generateToken(user.get());

        tokenRepositry.save(token);


       return token;
    }

    private Token generateToken(User user) {

        Token token = new Token();

        token.setUser(user);

        token.setValue(RandomStringUtils.randomAlphanumeric(10));

        LocalDate ld = LocalDate.now();

        LocalDate thirtyDaysLater =  ld.plusDays(30);

        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        token.setExpiryAt(expiryDate);

        return token;


    }

    public User validateToken(String value){

        Optional<Token> token = tokenRepositry.findByValueAndExpiryAtGreaterThan(value,new Date());

        if(token.isEmpty()){
            throw  new RuntimeException("User is not valid");
        }

        return token.get().getUser();
    }
}
