package cn.error0.controller;


import cn.error0.entity.Article;
import cn.error0.entity.SortInfo;
import cn.error0.service.ArticleService;
import cn.error0.util.Result;
import cn.error0.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BackController {


    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @ApiOperation("文章添加")
    @PostMapping("/write")
    public Result writeArticle(@RequestBody Article article) {
        try {
            articleService.addArticle(article);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success();
    }

    @ResponseBody
    @ApiOperation("文章编辑更新")
    @PostMapping("/aticle/update")
    public Result updateArticle(@RequestBody Article article) {
        try {
            articleService.updateArtcicle(article);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success();
    }

    @ApiOperation("文章管理")
    @PostMapping("/articlePage")
    @ResponseBody
    public PageInfo ArticlePage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
       Page page=PageHelper.startPage(pageNum, 5);
        List<Article> list = articleService.getAllArticle();
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @ApiOperation("文章类型管理")
    @PostMapping("/classPage")
    @ResponseBody
    public PageInfo ClassPage(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        Page page=PageHelper.startPage(pageNum, 5);
        List<SortInfo> list = articleService.getAllSort();
        PageInfo<SortInfo> pageInfo = new PageInfo<SortInfo>(list);
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @ResponseBody
    @ApiOperation("删除一条文章")
    @PostMapping("/Article/del")
    public Result delArticle(@RequestParam(name = "id") Long id) {

        try {
            articleService.delArticle(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success();
    }

    @ResponseBody
    @ApiOperation("批量删除文章")
    @PostMapping("/Articles/del")
        public Result delArticles(@RequestParam(name = "strlist") String strlist) {

        List<Long> ids=new ArrayList<>();
        String[] strs = strlist.split(",");
        for(String str:strs)
        {
            ids.add(Long.parseLong(str));
        }
        try {
            articleService.delArticles(ids);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success();
    }

    @PostMapping("/Article/sort")
    @ResponseBody
    @ApiOperation("文章分类")
    public Result<List<SortInfo>> InfoSort()
    {

        return ResultUtil.success(articleService.getAllSort());
    }


    @PostMapping("/Article/sort/add")
    @ResponseBody
    @ApiOperation("文章分类添加")
    public Result addSort(String sortName)
    {
        try {
            if(sortName==null)
            {
                return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
            }
            SortInfo sortInfo=new SortInfo();
            sortInfo.setName(sortName);
            articleService.addArticleSort(sortInfo);
            return ResultUtil.success();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.FAIL_CODE,ResultUtil.FAIL_MSG);
        }
    }

    @ResponseBody
    @ApiOperation("批量删除文章")
    @PostMapping("/Articles/sort/del")
    public Result delSort(@RequestParam(name = "strlist") String strlist) {

        List<Long> ids=new ArrayList<>();
        String[] strs = strlist.split(",");
        for(String str:strs)
        {
            ids.add(Long.parseLong(str));
        }
        try {
            articleService.delArtticleSort(ids);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success();
    }
}
