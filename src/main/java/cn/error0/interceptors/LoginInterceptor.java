package cn.error0.interceptors;
import cn.error0.entity.User;
import cn.error0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }else {
            User Isuser=userService.getByName(user.getUsername());
          if(Isuser!=null&&Isuser.getPassword().equals(user.getPassword()))
          {
            return true;
          }
          else return false;
        }
    }
}