package com.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    public static void writeLog(String comments, String info) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\xiaoliu\\Desktop\\info.txt");
            writer.write("[ " + comments + " ] [ " + info + " ] [ " + new Date() + " ]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
