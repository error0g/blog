package cn.error0.service;

import cn.error0.entity.Article;

import java.util.List;


public interface ArticleService {
    void addArticle(Article article);

    Article getOneArticle(Long id);

    List<Article> getAllArticle();
}
