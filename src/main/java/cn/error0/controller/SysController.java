package cn.error0.controller;

import cn.error0.entity.User;
import cn.error0.service.SysService;
import cn.error0.service.UserService;
import cn.error0.util.Result;
import cn.error0.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RestController
@RequestMapping("/admin")
public class SysController  {

    @Autowired
    private UserService userService;

    @Autowired
    private SysService sysService;

    private DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm ");

    //后台系统信息
    @PostMapping("/sys/Info")
    public  Result<Map <String,Object>>  Info(HttpServletRequest request){
        Map <String,Object> result=new HashMap<>();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            result.put("user",user.getUsername());
            result.put("lastTime",df.format(user.getLastTime()));
            result.put("articleNum", sysService.getAllArticleNum());
            result.put("sortNum", sysService.getAllSortNum());
            result.put("article",sysService.getTop5Article());
        }catch (Exception e)
        {
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.success(result);
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/modifyPwd")
    @ResponseBody
    public Result changeUserPassword(@RequestBody User user) {
        try {
            user.setPassword( DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userService.updateUser(user);
        } catch (Exception e) {
        return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
         }
        return ResultUtil.success();
    }

    /*
     *  功能：图片上传
     *  文件路径在Webconfig已经映射。
     * */
    @ApiOperation("图片上传")
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile editor) {

        Map<String, Object> result=new HashMap<>();
        try {
            String fileName = editor.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filepath = "D:\\picture\\";
            fileName = UUID.randomUUID() + suffixName;
            result.put("url", "/picture/" + fileName);
            File dest = new File(filepath + fileName);
            result.put("success",1);
            result.put("message","上传成功");
            editor.transferTo(dest);
        } catch (IOException e) {
            result.put("success",0);
            result.put("message",e.getMessage());
        }
        return  result;
    }


}
