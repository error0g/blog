package cn.error0.dao;


import cn.error0.entity.ArticleContent;
import cn.error0.entity.ArticleInfo;

import java.util.List;

public interface ArticleContentDao {

    int insert(ArticleContent articleContent);

    void delete(Long id);

    ArticleContent selectOne(Long id);

    List<ArticleContent> selectAll();

    void update(ArticleContent articleContent);
}
