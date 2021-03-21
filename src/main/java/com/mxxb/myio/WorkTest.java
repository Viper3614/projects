package com.mxxb.myio;

import org.junit.Test;

import java.io.*;

/**
 * @author Viper
 * @description
 * @date 2021/3/20
 */

public class WorkTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);   //把System.in 的流转换成BufferedReader流
        while (true) {
            System.out.println("请输入字符：");
            String data = bufferedReader.readLine();
            if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                System.out.println("程序3秒后退出");

                System.out.println(3);
                Thread.sleep(1000);
                System.out.println(2);
                Thread.sleep(1000);
                System.out.println(1);
                Thread.sleep(1000);
                System.out.println("bye!");
                break;
            }
            System.out.println(data.toUpperCase());
        }
    }

    @Test
    public void test3(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("D:/a.txt");
            ps = new PrintStream(fos);
            FileInputStream fis = new FileInputStream("d:/b.txt");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer))!=-1) {
                ps.print(buffer);
            }
            fis.close();
            ps.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}