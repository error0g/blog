package cn.error0.service;

import cn.error0.entity.User;
import org.springframework.stereotype.Service;

/**
 *  用户service
 */
public interface UserService {

    User getByName(String name);
}
