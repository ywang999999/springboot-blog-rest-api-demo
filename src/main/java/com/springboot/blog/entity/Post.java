package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "Posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
