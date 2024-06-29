package org.microservice.userserviceauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDtos {

   private String email;
   private String password;
}
