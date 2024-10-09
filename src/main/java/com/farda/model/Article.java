package com.farda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name="articles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title"),
        @UniqueConstraint(columnNames = "content")
})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 100)
    private String title;

    @Nonnull
    @Column(length = 1000)
    private String content;

    @Nonnull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private User author;

    @Nonnull
    public User getAuthor() {
        return author;
    }

    public void setAuthor(@Nonnull User author) {
        this.author = author;
    }

    public Article(){}

    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }


    @Nonnull
    public String getContent() {
        return content;
    }

    public void setContent(@Nonnull String content) {
        this.content = content;
    }
}
