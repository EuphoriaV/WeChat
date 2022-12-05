package com.example.repository;

import com.example.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM `message` WHERE (to_id=?1 and from_id=?2) or (to_id=?2 and from_id=?1)ORDER BY creation_time DESC", nativeQuery = true)
    List<Message> findMessages(long a, long b);

    @Query(value = "SELECT login FROM `user` WHERE id in ((SELECT from_id FROM `message` WHERE to_id=?1) UNION (SELECT to_id FROM `message` WHERE from_id=?1))", nativeQuery = true)
    List<String> findFriends(long a);
}
