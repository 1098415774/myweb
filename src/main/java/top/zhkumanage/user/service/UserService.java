package top.zhkumanage.user.service;

import top.zhkumanage.user.entity.User;

public interface UserService {
    User selectByUser(User user);
    User selectByUserName(String name);
    int insert(User user);
}
