package cn.error0.dao;

import cn.error0.entity.ArticleSort;

public interface ArticleSortDao {
    int insert(ArticleSort record);
    ArticleSort selectOne(Long id);
}
