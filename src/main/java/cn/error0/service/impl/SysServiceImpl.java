package cn.error0.service.impl;

import cn.error0.dao.ArticleContentDao;
import cn.error0.dao.ArticleInfoDao;
import cn.error0.dao.SortInfoDao;
import cn.error0.entity.*;
import cn.error0.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SortInfoDao sortInfoDao;

    @Autowired
    ArticleInfoDao articleInfoDao;
    @Autowired
    ArticleContentDao articleContentDao;


    @Override
    public int getAllSortNum() {
        return sortInfoDao.selectSortNum();
    }

    @Override
    public int getAllArticleNum() {
        return sortInfoDao.selectArticleNum();
    }

    @Override
    public List<Article> getTop5Article() {
        List<Article> ArticleList=new ArrayList();
        List<ArticleContent> allArticleContent=articleContentDao.selectAll();
        for(int i=0;(i<allArticleContent.size()&&i<5);i++)
        {
            Article article=new Article();
            ArticleInfo articleInfo=articleInfoDao.selectOne(allArticleContent.get(i).getArtcleId());
            ArticleContent articleContent=articleContentDao.selectOne(allArticleContent.get(i).getArtcleId());
            article.setId(allArticleContent.get(i).getArtcleId());
            article.setTitle(articleInfo.getTitle());
            article.setTop(articleInfo.isTop());
            article.setCreateDate(articleContent.getCreateDate());
            article.setTraffic(articleInfo.getTraffic());
            article.setContent(articleContent.getContent());
            ArticleList.add(article);
        }
        return ArticleList;
    }

}
