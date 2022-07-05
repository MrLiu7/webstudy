package com.web.servlet;

import com.domain.User;
import com.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置request的默认编码，以防乱码
        request.setCharacterEncoding("UTF-8");
        // 先验证验证码
        // 前端验证码
        String verifyCode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 及时移除session中的数据，保证验证码的一次性
        session.removeAttribute("CHECKCODE_SERVER");
        // 检测后端验证码是否为空
        if (checkcode_server == null) {
            // 设置登录信息
            request.setAttribute("login_msg", "验证码已过期");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        // 检测验证码是否正确
        if (!checkcode_server.equalsIgnoreCase(verifyCode)) {
            // 验证码不正确
            request.setAttribute("login_msg", "验证码错误");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 检测数据是否正确
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 查询数据库
        boolean isFind = new UserServiceImpl().findUser(user);
        if (isFind) {
            // 查找成功
            request.setAttribute("login_msg", "登陆成功");
            // 重定向
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // 查找失败
            request.setAttribute("login_msg", "账号或密码错误");
            // 转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
