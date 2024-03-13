package com.StudDept.repository;

import com.StudDept.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "select t from Token t inner join User u on t.user.id = u.userId where u.userId = :userId and (t.revoked = false or t.expired =false )")
    List<Token> findTokenByUser(@Param("userId") Long userId);

    Optional<Token> findUserByToken(String token);
}
