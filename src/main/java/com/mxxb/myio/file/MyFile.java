package com.mxxb.myio.file;

import org.junit.Test;

import java.io.*;

/**
 * @author Viper
 * @description
 * @date 2021/3/20
 */

public class MyFile {
    /*
    *   编码问题
    *       1个字节 byte  =  8 bit ，位
    *
    *   各种编码表
    *       ASCII           美国信息交换标准代码 ，用一个字节的7位可以表示
    *       ISO8859-1       欧洲码表，拉丁码表，  用一个字节的8位表示
    *
    *           中文编码表，最多占两个字节。根据首位的值判断是 中文编码 还是英文编码  （0，1）
            *       GB2312          中国的中文码表，最多两个字节编码所有字符
            *       GBK             GB2312的升级版，支持更多的中文，最多两个字节表示  （简体，繁体）
            *       Unicode         国际编码表，融合了目前人类使用的所有字符。所有的文字都使用两个字节来表示
    *       UTF-8           变长的编码方式，可以使用1~4个字节来表示   UCS Transfer Format
    */
    public static void main(String[] args) throws IOException {
        File file = new File("demo.txt");
        file.createNewFile();
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("dust.txt");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fis.close();
    }

    @Test
    public void test() {
        File file = new File("./src/main/java/com/mxxb/myio/file/demo1.txt");
        try {
            file.createNewFile();
            System.out.println(file.getAbsolutePath());
            FileReader fileReader = new FileReader(file);
//            int read = fileReader.read();
//            while (read!=-1) {
//                System.out.print((char)read);
//                read = fileReader.read();
//            }
            int len;
            while ((len = fileReader.read()) != -1) {
                System.out.print((char) len);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try {
            FileInputStream fis = new FileInputStream(new File("src/main/java/com/mxxb/myio/file/demo1.txt"));
            int len;
            byte[] buffer = new byte[5];
            while ((len = fis.read()) != -1) {
                String string = new String(buffer,0,len);
                System.out.println(string);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test2(){
        InputStream inputStream = System.in;

    }
}