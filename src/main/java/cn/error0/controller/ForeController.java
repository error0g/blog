package cn.error0.controller;

import cn.error0.entity.Article;
import cn.error0.service.impl.ArticleServiceImpl;
import cn.error0.util.Result;
import cn.error0.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ForeController implements ErrorController {

    @Autowired
    ArticleServiceImpl articleService;

    @ApiOperation("通过文章ID获取文章信息")
    @ResponseBody
    @GetMapping("article/{id}")
    public Result<Article> getArticleById(@PathVariable Long id) {
        Article article;
        try {
           article = articleService.getOneArticle(id);
        }
        catch (Exception e)
        {
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success(article);
    }

    @ApiOperation("文章分页")
    @GetMapping("/page")
    @ResponseBody
    public PageInfo findArticlePage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        Page page= PageHelper.startPage(pageNum, 3,"top desc");
        List<Article> list = articleService.getAllArticle();
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @Override
    public String getErrorPath() {
        return "/404";
    }
}
