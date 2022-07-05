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
    private final UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用dao查询 返回查询集合
        return dao.findAll();
    }

    @Override
    public boolean addUser(User user) {
        //执行添加操作
        UserDao addUserDao = new UserDaoImpl();
        return addUserDao.addUser(user);
    }

    @Override
    public boolean findUser(User user) {
        //查询dao 返回User对象
        User finUser = dao.findUserByNameAndPassword(user.getName(), user.getPassword());
        return finUser!=null;
    }
}
