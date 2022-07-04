package com.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verCodeServlet")
public class VerCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");

        //在内存中创建一个长为100，宽为30的图片，默认黑色背景
        int width = 100;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取图片的画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.gray);
        //填充图片
        g.fillRect(0, 0, width, height);

        //获取四位长度验证码
        String checkCode = getCheckCode(4);
        //将验证码放到HttpSession中
        req.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的大小
        g.setFont(new Font("黑体", Font.BOLD, 24));
        //在图片上绘制验证码
        g.drawString(checkCode, 15, 25);

        //将图片输出到浏览器
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    /**
     * 获取指定长度的随机验证码
     *
     * @return String 指定长度验证码
     */
    private String getCheckCode(int length) {
        StringBuilder sb = new StringBuilder();
        String codeFather = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(codeFather.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
