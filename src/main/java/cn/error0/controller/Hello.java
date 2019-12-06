package cn.error0.controller;

import cn.error0.entity.User;
import cn.error0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Hello {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/hello/{name}",method = RequestMethod.GET)
    public String echoHello(@PathVariable("name") String name)
    {
        System.out.println(name+"==================================");
        User user=userService.getByName(name);
        return user.toString();
    }
}