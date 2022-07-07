package com.service;

import com.domain.PageBean;
import com.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public interface UserService {
    /**
     * 查询所有用户信息接口
     *
     * @return 返回数据库中所有数据，以List集合的形式
     */
    List<User> findAll();

    /**
     * 添加用户信息
     * @param user 封装的用户对象
     * @return 返回布尔值，添加成功为true，失败为false
     */
    boolean addUser(User user);

    /**
     * 查找用户
     * @param user 封装的用户对象
     * @return 返回布尔值，查找到返回true，否则为false
     */
    boolean findUser(User user);

    /**
     * 删除用户
     * @param id 用户的id值
     * @return 删除成功为true，失败为false
     */
    boolean deleteUser(String id);

    /**
     * 通过用户id查找数据
     * @param id 用户id
     * @return 返回数据库中存在的数据，返回的是封装的User对象
     */
    User findUserByID(String id);

    /**
     * 更新用户信息
     * @param user 封装的用户
     * @return 返回布尔值，更新成功为true，否则为false
     */
    boolean updateUser(User user);

    /**
     * 批量删除用户
     * @param userIds 用户的id字符串形式数组
     */
    void deleteUsers(String[] userIds);

    PageBean<User> findUserByPage(Map<String, String[]> parameterMap, String _findPage, String _rows);
}
