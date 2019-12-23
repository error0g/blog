package cn.error0.controller;

import cn.error0.entity.Article;
import cn.error0.service.impl.ArticleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForeController {

    @Autowired
    ArticleServiceImpl articleService;
    @ApiOperation("通过文章ID获取文章信息")
    @ResponseBody
    @GetMapping("article/{id}")
    public Article getArticleById(@PathVariable Long id)
    {
        Article article=articleService.getOneArticle(id);
        return article;
    }
}
