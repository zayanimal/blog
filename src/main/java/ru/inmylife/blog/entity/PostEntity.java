package ru.inmylife.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import ru.inmylife.blog.dto.block.Post;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Table(schema = "BLOG", name = "POST")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", schema = "BLOG", sequenceName = "POST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CREATED")
    private ZonedDateTime created;

    @Column(name = "POST")
    @Convert(converter = PostConverter.class)
    private Post post;

    @Column(name = "IS_PUBLIC")
    private Boolean isPublic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostEntity that = (PostEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
