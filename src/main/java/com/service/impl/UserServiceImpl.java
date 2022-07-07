package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.PageBean;
import com.domain.User;
import com.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author 柳继纪
 * @date 3/7/2022
 * @apiNote
 */
public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        // 调用dao查询 返回查询集合
        return dao.findAll();
    }

    @Override
    public boolean addUser(User user) {
        // 执行添加操作
        UserDao addUserDao = new UserDaoImpl();
        return addUserDao.addUser(user);
    }

    @Override
    public boolean findUser(User user) {
        // 查询dao 返回User对象
        User finUser = dao.findUserByNameAndPassword(user.getName(), user.getPassword());
        return finUser != null;
    }

    @Override
    public boolean deleteUser(String id) {
        return dao.deleteUserByID(Integer.parseInt(id)) != 0;
    }

    @Override
    public User findUserByID(String id) {
        return dao.findUserByID(Integer.parseInt(id));
    }

    @Override
    public boolean updateUser(User user) {
        // 调用dao查询数据库
        return dao.updateUser(user) != 0;
    }

    @Override
    public void deleteUsers(String[] userIds) {
        for (String userId : userIds) {
            // 调用dao的删除操作
            dao.deleteUserByID(Integer.parseInt(userId));
        }
    }

    /**
     * 分页查询
     *
     * @param parameterMap 查询条件
     * @param _findPage    待查询的页码
     * @param _rows        每页显示的条数
     * @return 返回封装的数据对象
     */
    @Override
    public PageBean<User> findUserByPage(Map<String, String[]> parameterMap, String _findPage, String _rows) {
        // 转化数据
        int findPage = (_findPage == null || "".equals(_findPage)) ? 1 : Integer.parseInt(_findPage);
        int rows = (_rows == null || "".equals(_rows)) ? 10 : Integer.parseInt(_rows);
        //根据参数封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        // 查询总记录
        int allRecord = dao.findAllRecord(user);
        // 计算总页码
        int pageCount = allRecord % rows == 0 ? allRecord / rows : allRecord / rows + 1;
        // 移除非法数据
        if (findPage < 1) {
            findPage = 1;
        } else if (findPage > pageCount) {
            findPage = pageCount;
        }
        // 开始查询的数据下标
        int start = (findPage - 1) * rows;
        // 查询数据
        List<User> list = dao.findUserByPage(user,start, rows);
        // 总记录条数 总页码 当前页面 每页条数 数据集合
        return new PageBean<>(allRecord, pageCount, findPage, rows, list,user);
    }
}
