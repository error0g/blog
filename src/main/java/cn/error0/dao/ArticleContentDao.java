package cn.error0.dao;


import cn.error0.entity.ArticleContent;

import java.util.List;

public interface ArticleContentDao {

    int insert(ArticleContent record);

    ArticleContent selectOne(Long id);

    List<ArticleContent> selectAll();
}
