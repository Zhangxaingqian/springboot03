package cn.myweb01.money01.service;

import cn.myweb01.money01.pojo.User;

public interface IUserService {
    void register(User user) throws Exception;

    Boolean active(String code);

    User login(User user) throws Exception;

    User queryUserByUid(Integer uid);

    Boolean insertUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUserByUid(Integer uid);
}
