package com.example.repository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET passwordSha=SHA1(CONCAT('1be3db47a7684152', ?2, ?3)) WHERE id=?1", nativeQuery = true)
    void updatePasswordSha(long id, String login, String password);

    @Query(value = "SELECT * FROM user WHERE login=?1 AND passwordSha=SHA1(CONCAT('1be3db47a7684152', ?1, ?2))", nativeQuery = true)
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    @Query(value = "SELECT * FROM user WHERE (LOWER(login) LIKE %?1%) OR (LOWER(name) LIKE %?1%)", nativeQuery = true)
    List<User> findAllUsers(String str);
}
