package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "post")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "body")
    @NonNull
    private String body;

    @Column(name = "date")
    @NonNull
    private Date dateCreated;

    @ManyToOne
    private User creator;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
