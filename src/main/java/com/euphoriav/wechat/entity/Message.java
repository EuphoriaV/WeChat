package com.euphoriav.wechat.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message", schema = "wechat")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id_from")
    private User from;

    @ManyToOne
    @JoinColumn(name = "user_id_to")
    private User to;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
