package cn.error0.dao;


import cn.error0.entity.ArticleContent;
import cn.error0.entity.ArticleInfo;

public interface ArticleContentDao {
     int insert(ArticleContent record);
     ArticleContent selectOne(Long id);
}
