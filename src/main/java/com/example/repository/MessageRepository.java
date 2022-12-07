package com.example.repository;

import com.example.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM `message` WHERE (to_id=?1 and from_id=?2) or (to_id=?2 and from_id=?1)ORDER BY creation_time DESC", nativeQuery = true)
    List<Message> findMessages(long a, long b);

    // По какой-то причине hibernate не может кастануть Object[] к User, поэтому пришлось делать это вручную
    @Query(value = "SELECT id, login, name FROM ( SELECT * FROM `user` AS b JOIN  ((SELECT creation_time, to_id FROM `message` WHERE from_id=?1) UNION \n" +
            "(SELECT creation_time, from_id FROM `message` WHERE to_id=?1)) as a on b.id=a.to_id) a GROUP BY id ORDER BY MAX(creation_time) DESC", nativeQuery = true)
    List<Object[]> findFriends(long a);

    @Query(value = "SELECT * FROM `message` WHERE (from_id=?1 and to_id=?2) or (to_id=?1 and from_id=?2) ORDER BY creation_time DESC LIMIT 1", nativeQuery = true)
    Message findLastMessage(long a, long b);
}
