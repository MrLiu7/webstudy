package com.web.servlet;

import com.Log.Log;
import com.domain.User;
import com.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        // 根据表单创建user
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        // 调用servlet更新信息
        boolean success = new UserServiceImpl().updateUser(user);
        if (success) {
            // 更新成功，重定向到全部显示
            response.sendRedirect(request.getContextPath() + "/userListServlet");
        } else {
            // 失败，转发到更改界面
            // 先共享user
            request.setAttribute("user", user);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        }
    }
}
