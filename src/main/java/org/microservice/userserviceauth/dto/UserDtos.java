package org.microservice.userserviceauth.dto;

import lombok.Getter;
import lombok.Setter;
import org.microservice.userserviceauth.Models.Role;
import org.microservice.userserviceauth.Models.User;

import java.util.List;


@Getter
@Setter
public  class UserDtos {

    private String name;
    private String email;

    private List<Role> roles;

    public static  UserDtos from(User user){
        UserDtos userDtos = new UserDtos();

        userDtos.setEmail(user.getEmail());
        userDtos.setName(user.getName());
        userDtos.setRoles(user.getRoleList());

        return  userDtos;
    }
}
