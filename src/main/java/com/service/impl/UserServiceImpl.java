package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.User;
import com.service.UserService;

import java.util.List;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao查询
        List<User> users = dao.findAll();
        return users;
    }
}
