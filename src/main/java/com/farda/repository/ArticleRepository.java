package com.farda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farda.model.Article;

@Repository
public interface ArticleRepository  extends JpaRepository<Article, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Article> findByUserId(Long userId);

    List<Article> findAll();

}