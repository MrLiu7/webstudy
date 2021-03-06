package com.dao;

import com.domain.User;

import java.util.List;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public interface UserDao {
     List<User> findAll();
     boolean addUser(User user);

     User findUserByNameAndPassword(String name,String password);

    int deleteUserByID(int id);

    User findUserByID(int id);

    int updateUser(User user);

    List<User> findUserByPage(User user, int start, int rows);

    int findAllRecord(User user);
}
