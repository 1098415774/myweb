package top.zhkumanage.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhkumanage.user.dao.UserMapper;
import top.zhkumanage.user.entity.User;

@Service("userServiceIMP")
public class UserServiceIMP implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUser(User user) {
        return userMapper.selectByUser(user);
    }

    @Override
    public User selectByUserName(String name) {
        return userMapper.selectByUserName(name);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
