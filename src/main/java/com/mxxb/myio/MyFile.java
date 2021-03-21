package com.mxxb.myio;

import java.io.File;
import java.io.IOException;

/**
 * @author Viper
 * @description
 * @date 2021/3/19
 */

public class MyFile {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\WorkApp\\java\\jdk1.8");
        readFile(file);
    }

    private static void readFile(File file) {
        File[] listFiles = file.listFiles();

        for (File f : listFiles) {
            if (f.isFile()) {
                if (f.getName().endsWith("exe")) {
                    System.out.println("当前目录：[" + f.getParent().substring(f.getParent().lastIndexOf("\\") + 1, f.getParent().length()) + "]，文件名：" + f.getName() + ",[count] : " + ++count);
                }

            } else if (f.isDirectory()) {
                readFile(f);
            }
        }
    }
}