package com.euphoriav.wechat.repository;

import com.euphoriav.wechat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

    @Query("from User u where u.login ilike :substr or u.name ilike :substr")
    List<User> getUsersBySubstr(@Param("substr") String substr);
}
