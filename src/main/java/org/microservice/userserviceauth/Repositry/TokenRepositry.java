package org.microservice.userserviceauth.Repositry;

import org.microservice.userserviceauth.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface TokenRepositry extends JpaRepository<Token,Long> {
    Optional<Token> findByValueAndExpiryAtGreaterThan(String value, Date date);

    Optional<Token> findByValue(String value);
}
