package cn.error0.service;

import cn.error0.entity.User;



public interface UserService {

    User getByName(String name);

    void updateUser(User user);
}
