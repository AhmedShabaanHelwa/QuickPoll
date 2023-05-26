package com.example.quickpoll.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long id;
    @Column(name = "QUESTION")
    private String question;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    private Set<Option> options;
}
