package ru.inmylife.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import ru.inmylife.blog.dto.block.PostData;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Table(schema = "BLOG", name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", schema = "BLOG", sequenceName = "POST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "POST")
    @Convert(converter = PostConverter.class)
    private PostData postData;

    @OneToOne(fetch = FetchType.EAGER)
    private Topic topic;

    @Column(name = "IS_PUBLIC")
    private Boolean isPublic;

    @Column(name = "CREATED")
    private ZonedDateTime created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post that = (Post) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
