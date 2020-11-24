package org.example.demo.other.web.article.service.interf;

import org.example.demo.other.web.article.model.Article;

import java.util.List;

public interface ArticleServiceInterf {

    Article save(Article article);

    void deleteById(String id);

    Article findById(String id);

    List<Article> findAll();
}
