package cn.error0.dao;

import cn.error0.entity.ArticleInfo;

public interface ArticleInfoDao {
    Long insert(ArticleInfo record);
    ArticleInfo selectOne(Long id);
}
