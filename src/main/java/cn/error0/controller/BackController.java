package cn.error0.controller;

import cn.error0.entity.User;
import cn.error0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class BackController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index()
    {
        return "admin/index";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit()
    {
        return "admin/edit";
    }

    @PutMapping("user/{username}")
    public String changeUserPassword(@PathVariable String  username)
    {

        return null;
    }

}
