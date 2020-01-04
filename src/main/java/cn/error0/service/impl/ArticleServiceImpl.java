package cn.error0.service.impl;

import cn.error0.dao.ArticleContentDao;
import cn.error0.dao.ArticleInfoDao;
import cn.error0.dao.ArticleSortDao;
import cn.error0.dao.SortInfoDao;
import cn.error0.entity.*;
import cn.error0.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleContentDao articleContentDao;
    @Autowired
    ArticleSortDao articleSortDao;
    @Autowired
    SortInfoDao sortInfoDao;

    @Override
    public void addArticle(Article article) {

        /*添加标题*/
        ArticleInfo articleInfo=new ArticleInfo();
        articleInfo.setTitle(article.getTitle());
        articleInfo.setTop(article.isTop());
        articleInfoDao.insert(articleInfo);
        /*添加文章*/
        ArticleContent articleContent=new ArticleContent();
        articleContent.setArtcleId(articleInfo.getId());
        articleContent.setContent(article.getContent());
        articleContentDao.insert(articleContent);
        /*分类信息*/
        ArticleSort articleSort=new ArticleSort();
        articleSort.setArtcleId(articleInfo.getId());
        articleSort.setSortId(article.getKindId());
        articleSortDao.insert(articleSort);
    }

    @Override
    public Article getOneArticle(Long id) {
        Article article=new Article();
        ArticleInfo articleInfo=articleInfoDao.selectOne(id);
        ArticleContent articleContent=articleContentDao.selectOne(id);
        ArticleSort articleSort=articleSortDao.selectOne(id);
        SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
        //访问量+1
        articleInfo.setTraffic(articleInfo.getTraffic()+1);
        articleInfoDao.updateArticleInfo(articleInfo);
        article.setId(id);
        article.setTitle(articleInfo.getTitle());
        article.setTop(articleInfo.isTop());
        article.setCreateDate(articleContent.getCreateDate());
        article.setTraffic(articleInfo.getTraffic());
        article.setContent(articleContent.getContent());
        article.setKindId(articleSort.getSortId());
        article.setKindName(sortInfo.getName());
        return article;
    }

    @Override
    public List<Article> getAllArticle() {
        List<Article> allArticle=new ArrayList();
        List<ArticleContent> allArticleContent=articleContentDao.selectAll();
        for(int i=0;i<allArticleContent.size();i++)
        {
            Article article=new Article();
            ArticleInfo articleInfo=articleInfoDao.selectOne(allArticleContent.get(i).getArtcleId());
            ArticleContent articleContent=articleContentDao.selectOne(allArticleContent.get(i).getArtcleId());
            ArticleSort articleSort=articleSortDao.selectOne(allArticleContent.get(i).getArtcleId());
            SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
            article.setId(allArticleContent.get(i).getArtcleId());
            article.setTitle(articleInfo.getTitle());
            article.setTop(articleInfo.isTop());
            article.setCreateDate(articleContent.getCreateDate());
            article.setTraffic(articleInfo.getTraffic());
            article.setContent(articleContent.getContent());
            article.setKindId(articleSort.getSortId());
            article.setKindName(sortInfo.getName());
            allArticle.add(article);
        }
        return allArticle;
    }
}
