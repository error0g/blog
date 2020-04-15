package cn.error0.dao;

import cn.error0.entity.ArticleSort;

public interface ArticleSortDao {
    int insert(ArticleSort record);

    void delete(Long id);

    ArticleSort selectBySortId(Long id);

    ArticleSort selectByArticleId(Long id);

    void update(ArticleSort record);
}
