package com.euphoriav.wechat.repository;

import com.euphoriav.wechat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(" from Message m " +
            "where (m.from.id = :user1Id and m.to.id = :user2Id) " +
            "   or (m.from.id = :user2Id and m.to.id = :user1Id) " +
            "order by m.createdAt")
    List<Message> getMessagesBetween(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    @Query(value = "select distinct on (case when user_id_to = :userId then user_id_from " +
            "                                when user_id_from = :userId then user_id_to end) * " +
            "       from wechat.message " +
            "       where (case when user_id_to = :userId then user_id_from " +
            "                   when user_id_from = :userId then user_id_to end, created_at) in " +
            "           (select case when user_id_to = :userId then user_id_from " +
            "                        when user_id_from = :userId then user_id_to end as user_with, " +
            "               max(created_at) as created_at " +
            "           from wechat.message " +
            "           where user_id_from = :userId or user_id_to = :userId " +
            "           group by user_with)", nativeQuery = true)
    List<Message> getLastMessages(@Param("userId") Long userId);
}
