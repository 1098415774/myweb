package top.zhkumanage.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhkumanage.user.dao.UserStatusMapper;
import top.zhkumanage.user.entity.UserStatus;
@Service("userStatusServiceIMP")
public class UserStatusServiceIMP implements UserStatusService{
    @Autowired
    private UserStatusMapper userStatusMapper;

    @Override
    public UserStatus selectByPrimaryKey(Integer iduser) {
        return userStatusMapper.selectByPrimaryKey(iduser);
    }
}
