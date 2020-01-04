package cn.error0.dao;

import cn.error0.entity.User;


public interface UserDao {
    User selectByName(String name);
    void updateUser(User user);
}
