package cn.error0.service;

import cn.error0.entity.Article;

import java.util.List;

public interface SysService {
    int getAllSortNum();
    int getAllArticleNum();
    List<Article> getTop5Article();

}
