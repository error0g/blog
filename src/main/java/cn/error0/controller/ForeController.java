package cn.error0.controller;

import cn.error0.entity.Article;
import cn.error0.service.impl.ArticleServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "前台Api")
@Controller
public class ForeController {

    @Autowired
    ArticleServiceImpl articleService;

    @ApiOperation("通过文章ID获取文章信息")
    @ResponseBody
    @GetMapping("article/{id}")
    public Article getArticleById(@PathVariable Long id) {
        Article article = articleService.getOneArticle(id);
        return article;
    }

    @ApiOperation("文章分页")
    @GetMapping("/page")
    @ResponseBody
    public PageInfo getArticlePage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Article> list = articleService.getAllArticle();
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        return pageInfo;
    }

}
