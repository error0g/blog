package cn.error0.controller;

import cn.error0.entity.User;
import cn.error0.service.UserService;
import io.swagger.annotations.Api;
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

@Api(tags = "登陆Api")
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @ApiOperation("用户登陆")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(User users, HttpServletRequest request)  {
        User user = userService.getByName(users.getUsername());
        if (user != null) {
            /*MD5加密*/
            String usersPssword = DigestUtils.md5DigestAsHex(users.getPassword().getBytes());
            if (user.getPassword().equals(usersPssword)) {
                request.getSession().setAttribute("user", user);
                return true;
            }
        }
        return false;
    }

    /*用户注销*/
    @ApiOperation("用户注销")
    @ResponseBody
    @PostMapping(value = "/quit")
    public String quit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return null;
    }
}
