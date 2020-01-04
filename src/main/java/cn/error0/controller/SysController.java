package cn.error0.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SysController implements ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @Override
    public String getErrorPath() {
        return "/404";
    }
}
