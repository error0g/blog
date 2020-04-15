package cn.error0.service;

import cn.error0.entity.Article;
import cn.error0.entity.SortInfo;

import java.util.List;


public interface ArticleService {
    void addArticle(Article article);

    void delArticle(long id);

    void delArticles(List<Long> idArr);

    void updateArtcicle(Article article);

    Article getOneArticle(Long id);

    List<Article> getAllArticle();

    List<SortInfo> getAllSort();

    void addArticleSort(SortInfo sortInfo);

    void delArtticleSort(List<Long> SortId);
}
