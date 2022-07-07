package com.util;

import com.dao.impl.UserDaoImpl;

public class BombardmenDatabase implements Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new BombardmenDatabase()).start();
        }
    }

    @Override
    public void run() {
        UserDaoImpl userDao = new UserDaoImpl();
        while (true) {
            userDao.findAll();
        }
    }
}
