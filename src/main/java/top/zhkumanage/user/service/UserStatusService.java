package top.zhkumanage.user.service;

import top.zhkumanage.user.entity.UserStatus;

public interface UserStatusService {
    UserStatus selectByPrimaryKey(Integer iduser);
}
