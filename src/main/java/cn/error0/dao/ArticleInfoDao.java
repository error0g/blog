package cn.error0.dao;

import cn.error0.entity.ArticleContent;
import cn.error0.entity.ArticleInfo;

import java.util.List;


public interface ArticleInfoDao {
    Long insert(ArticleInfo record);

    void delete(Long id);

    ArticleInfo selectOne(Long id);

    void update(ArticleInfo articleInfo);

    List<ArticleInfo> selectAll();

}
