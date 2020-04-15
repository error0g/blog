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

        //添加标题
        ArticleInfo articleInfo=new ArticleInfo();
        articleInfo.setTitle(article.getTitle());
        articleInfo.setTop(article.isTop());
        articleInfoDao.insert(articleInfo);
        //添加文章
        ArticleContent articleContent=new ArticleContent();
        articleContent.setArtcleId(articleInfo.getId());
        articleContent.setContent(article.getContent());
        articleContentDao.insert(articleContent);
        //分类信息
        ArticleSort articleSort=new ArticleSort();
        articleSort.setArtcleId(articleInfo.getId());
        articleSort.setSortId(article.getKindId());
        articleSortDao.insert(articleSort);

        //文章数量+1
        SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
        sortInfo.setNumber(sortInfo.getNumber()+1);
        sortInfoDao.updateSortInfo(sortInfo);

    }

    @Override
    public void delArticle(long id) {
        deleteArticleById(id);
    }

    /*删除文章*/
    @Override
    public void delArticles(List<Long> idArr) {
        for(int i=0;i<idArr.size();i++)
        {
            deleteArticleById(idArr.get(i));
        }
    }

    @Override
    public void updateArtcicle(Article article) {
        //标题
        ArticleInfo articleInfo=articleInfoDao.selectOne(article.getId());
        articleInfo.setTitle(article.getTitle());
        articleInfo.setTop(article.isTop());
        articleInfoDao.update(articleInfo);
        //文章
        ArticleContent articleContent=new ArticleContent();
        articleContent.setArtcleId(article.getId());
        articleContent.setContent(article.getContent());
        articleContentDao.update(articleContent);

        ArticleSort oldArticleSort=articleSortDao.selectByArticleId(article.getId());

        //分类信息
        ArticleSort articleSort=new ArticleSort();
        articleSort.setArtcleId(article.getId());
        articleSort.setSortId(article.getKindId());
        articleSortDao.update(articleSort);


        SortInfo sortInfo=sortInfoDao.selectOne(oldArticleSort.getSortId());
        sortInfo.setNumber(sortInfo.getNumber()-1);
        sortInfoDao.updateSortInfo(sortInfo);
        sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
        sortInfo.setNumber(sortInfo.getNumber()+1);
        sortInfoDao.updateSortInfo(sortInfo);
    }

    @Override
    public Article getOneArticle(Long id) {
        Article article=new Article();
        ArticleInfo articleInfo=articleInfoDao.selectOne(id);
        ArticleContent articleContent=articleContentDao.selectOne(id);
        ArticleSort articleSort=articleSortDao.selectByArticleId(id);
        SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
        //访问量+1
        articleInfo.setTraffic(articleInfo.getTraffic()+1);
        articleInfoDao.update(articleInfo);
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
        List<ArticleInfo> allArticleInfo=articleInfoDao.selectAll();
        for(int i=0;i<allArticleInfo.size();i++)
        {
            Article article=new Article();
            ArticleContent articleContent=articleContentDao.selectOne(allArticleInfo.get(i).getId());
            ArticleSort articleSort=articleSortDao.selectByArticleId(allArticleInfo.get(i).getId());
            SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());


            article.setId(allArticleInfo.get(i).getId());
            article.setTitle(allArticleInfo.get(i).getTitle());
            article.setTop(allArticleInfo.get(i).isTop());
            article.setCreateDate(articleContent.getCreateDate());
            article.setTraffic(allArticleInfo.get(i).getTraffic());
            article.setContent(articleContent.getContent());
            article.setKindId(articleSort.getSortId());
            article.setKindName(sortInfo.getName());
            allArticle.add(article);
        }
        return allArticle;
    }

    public void deleteArticleById(Long id)
    {
        ArticleSort articleSort=articleSortDao.selectByArticleId(id);
        //删除文章内容
        articleContentDao.delete(articleSort.getArtcleId());
        //删除文章标题
        articleInfoDao.delete(articleSort.getArtcleId());
        //删除分类信息
        articleSortDao.delete(articleSort.getId());
        //文章-1
        SortInfo sortInfo=sortInfoDao.selectOne(articleSort.getSortId());
        sortInfo.setNumber(sortInfo.getNumber()-1);
        sortInfoDao.updateSortInfo(sortInfo);
    }
    @Override
    public List<SortInfo> getAllSort() {
        return sortInfoDao.selectAllSortInfo();
    }

    @Override
    public void addArticleSort(SortInfo sortInfo) {
        sortInfoDao.insert(sortInfo);
    }

    @Override
    public void delArtticleSort(List<Long> SortId)
    {
        for(int i=0;i<SortId.size();i++)
        {
            ArticleSort articleSort=articleSortDao.selectBySortId(SortId.get(i));
            System.out.println(articleSort.getArtcleId());
            if(articleSort!=null) {
                articleContentDao.delete(articleSort.getArtcleId());
                articleInfoDao.delete(articleSort.getArtcleId());
                articleSortDao.delete(articleSort.getId());
                sortInfoDao.delete(SortId.get(i));
            }
            else {
                sortInfoDao.delete(SortId.get(i));
        }

        }
    }
}
