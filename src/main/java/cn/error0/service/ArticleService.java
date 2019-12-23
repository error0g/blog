package cn.error0.service;

import cn.error0.entity.Article;

/**
 *  文章service
 */
public interface ArticleService {
    void addArticle(Article Article);
    Article getOneArticle(Long id);
}
