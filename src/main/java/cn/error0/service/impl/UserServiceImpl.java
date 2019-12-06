package cn.error0.service.impl;

import cn.error0.dao.UserDao;
import cn.error0.entity.User;
import cn.error0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User getByName(String name) {
        return userDao.selectByName(name);
    }
}
