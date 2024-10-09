package com.farda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 20)
    private String username;

    @Nonnull
    @Column(length = 50)
    private String email;

    @Nonnull
    @Column(length = 120)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Article> articlesList;

    public User (){}

    public  User (String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    @Nonnull
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nonnull String username) {
        this.username = username;
    }

    @Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nonnull String email) {
        this.email = email;
    }

    @Nonnull
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    public Collection<Article> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Article> articlesList) {
        this.articlesList = articlesList;
    }
}


