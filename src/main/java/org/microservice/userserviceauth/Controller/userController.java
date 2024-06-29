package org.microservice.userserviceauth.Controller;


import org.microservice.userserviceauth.Models.Token;
import org.microservice.userserviceauth.Models.User;
import org.microservice.userserviceauth.Repositry.TokenRepositry;
import org.microservice.userserviceauth.Service.UserService;
import org.microservice.userserviceauth.dto.LoginDtos;
import org.microservice.userserviceauth.dto.SignUpDto;
import org.microservice.userserviceauth.dto.UserDtos;
import org.microservice.userserviceauth.dto.ValidateDtos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/users")
public class userController {

 //   private UserDtos userDtos; not required as we are making from in dto as static function
    private UserService userService;

    private  userController(UserService userService ){
       // this.userDtos = userDtos;
        this.userService = userService;
    }


    @PostMapping("/signup")
    public UserDtos signup(@RequestBody SignUpDto requestDto){
        User user = userService.signUp(requestDto.getEmail(),requestDto.getName(),requestDto.getPassword());
        return UserDtos.from(user);
    }

    @PostMapping("/login")
    public Token signup(@RequestBody LoginDtos requestDto){
        return userService.login(requestDto.getEmail(),requestDto.getPassword());
    }

    @PostMapping("/validate")
    public UserDtos signup(@RequestBody ValidateDtos requestDto){
        return UserDtos.from(userService.validateToken(requestDto.getValue()));
    }

}
