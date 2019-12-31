package cn.error0.controller;


import cn.error0.entity.Article;
import cn.error0.service.ArticleService;
import cn.error0.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/admin")
public class BackController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService ArticleService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index()
    {
        return "admin/index";
    }

    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String writeArticle()
    {
        return "admin/edit";
    }

    @RequestMapping(value = "/article",method = RequestMethod.GET)
    public String saveArticle() { return "admin/article"; }


    @ResponseBody
    @ApiOperation("文章添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "文章内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "top", value = "文章置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "kindId", value = "文章分类", required = true, dataType = "Long"),
    })
    @PostMapping("/write")
    public Boolean write( @RequestBody Article article)
    {
        ArticleService.addArticle(article);
        System.out.println(article.toString());
        return true;
    }

    @PutMapping("user/{username}")
    public String changeUserPassword(@PathVariable String  username)
    {
        return null;
    }

    /*
    *  功能：图片上传
    *  文件路径在Webconfig已经映射。
    * */
    @RequestMapping(value="/uploadfile",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "editormd-image-file", required = false)MultipartFile editor )
    {
        Map<String, Object> result= new HashMap<>();

        try {
            String fileName=editor .getOriginalFilename();
            String suffixName=fileName.substring(fileName.lastIndexOf("."));
            String filepath="D:\\picture\\";
            fileName= UUID.randomUUID()+suffixName;
            result.put("success",1);
            result.put("message","上传成功");
            result.put("url","/picture/"+fileName);
            File dest=new File(filepath+fileName);
            editor.transferTo(dest);
        } catch (IOException e) {
            result.put("success","0");
            result.put("message","上传失败");
            e.printStackTrace();
        }
        return result;
    }

}
