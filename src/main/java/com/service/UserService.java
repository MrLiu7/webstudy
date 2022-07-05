package com.service;

import com.domain.User;

import java.util.List;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public interface UserService {
    /**
     * 查询所有用户信息接口
     * @return
     */
    public List<User> findAll();

    public boolean addUser(User user);

    public boolean findUser(User user);
}
