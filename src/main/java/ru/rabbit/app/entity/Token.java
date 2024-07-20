package ru.rabbit.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Table(schema = "BLOG", name = "PERSISTENT_LOGINS")
public class Token {

    @Id
    @Column(name = "SERIES")
    private String id;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "LAST_USED")
    private Date lastUsed;

    @Column(name = "DEVICE")
    private String device;
}
