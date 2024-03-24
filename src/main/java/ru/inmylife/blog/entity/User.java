package ru.inmylife.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Table(schema = "BLOG", name = "USERS")
public class User {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String username;

    @Column(name = "PASS")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "CREATED")
    private ZonedDateTime created;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        schema = "BLOG",
        name = "USER_TOPIC",
        joinColumns = @JoinColumn(name = "USER_ID"),
        foreignKey = @ForeignKey(name = "FK_USER_TOPIC_USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "TOPIC_ID"),
        inverseForeignKey = @ForeignKey(name = "FK_USER_TOPIC_TOPIC_ID"))
    private Set<Topic> topics;
}
