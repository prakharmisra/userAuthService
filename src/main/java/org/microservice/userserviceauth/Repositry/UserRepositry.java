package org.microservice.userserviceauth.Repositry;

import org.microservice.userserviceauth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Repository
public interface UserRepositry extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
