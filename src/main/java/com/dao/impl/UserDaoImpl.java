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
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        String sql = "select * from user";
        //此处User.class 需要让User有空构造函数 否则或报错
        //错误：Is it an abstract class?; nested exception is java.lang.InstantiationException
        //这种方法需要保证数据库字段名称和类的属性名称保持一致
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
