package cn.error0.controller;

import cn.error0.entity.User;
import cn.error0.service.UserService;
import cn.error0.util.Result;
import cn.error0.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @ApiOperation("用户登陆")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(User users, HttpServletRequest request)  {
        User user;
        try {
            user = userService.getByName(users.getUsername());
            if (user != null) {
                String usersPssword = DigestUtils.md5DigestAsHex(users.getPassword().getBytes());
                if (user.getPassword().equals(usersPssword)) {
                    request.getSession().setAttribute("user", user);
                    user.setLastTime(new Date());
                    userService.updateUser(user);
                    return ResultUtil.success();
                }
            }
        }catch (Exception e)
        {
            return ResultUtil.error(ResultUtil.Error_CODE,ResultUtil.Error_MSG);
        }
        return ResultUtil.error(ResultUtil.FAIL_CODE,ResultUtil.FAIL_MSG);
    }

    //用户注销
    @ApiOperation("用户注销")
    @ResponseBody
    @PostMapping(value = "/quit")
    public String quit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return null;
    }
}
