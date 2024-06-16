package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inmylife.blog.entity.Token;

import java.util.Date;

public interface TokenJpaRepository extends JpaRepository<Token, String> {

    void deleteAllByUsername(String username);

    @Modifying
    @Query(value = "update blog.persistent_logins set token = :token, last_used = :lastUsed where series = :series", nativeQuery = true)
    void updateToken(@Param("series") String series, @Param("token") String token, @Param("lastUsed") Date lastUsed);
}
