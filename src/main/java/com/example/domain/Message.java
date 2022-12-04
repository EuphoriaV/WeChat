package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1,max = 1024)
    private String text;

    @ManyToOne
    @JoinColumn(name = "from_id", nullable = false)
    private User from;

    @ManyToOne
    @JoinColumn(name = "to_id", nullable = false)
    private User to;
}
