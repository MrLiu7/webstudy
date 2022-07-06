package com.dao.impl;

import com.dao.UserDao;
import com.domain.User;
import com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        // 使用JDBC操作数据库
        String sql = "select * from user";
        // 此处User.class 需要让User有空构造函数 否则或报错
        // 错误：Is it an abstract class?; nested exception is java.lang.InstantiationException
        // 这种方法需要保证数据库字段名称和类的属性名称保持一致
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public boolean addUser(User user) {
        /*String sql = "insert into user values(null,'" + user.getName() + "',null,'" +
                user.getSex() + "'," + user.getAge() + ",'" + user.getAddress() + "','" +
                user.getQq() + "','" + user.getEmail() + "');";*/
        String sql = "insert into user values(null,?,?,?,?,?,?,?)";
        int lines = template.update(sql, user.getName(), user.getPassword(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
        return lines != 0;
    }

    @Override
    public User findUserByNameAndPassword(String name, String password) {
        try {
            String sql = "select * from user where name = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteUserByID(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        return template.update(sql, id);
    }

    /**
     * 通过id查询数据库
     *
     * @param id 整型，用户id
     * @return User 对象
     */
    @Override
    public User findUserByID(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set name = ?,password = '2677ljj',sex = ?,age = ?,address = ?,qq = ?,email = ? where id = ?";
        return template.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }
}
