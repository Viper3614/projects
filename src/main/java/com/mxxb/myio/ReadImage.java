package com.mxxb.myio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Viper
 * @description
 * @date 2021/3/19
 */

public class ReadImage {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F00.minipic.eastday.com%2F20170113%2F20170113135934_7637c79721ac3ad0ff2de3905f1aefc5_5.jpeg&refer=http%3A%2F%2F00.minipic.eastday.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618733774&t=9418880be736d6412db9780fa9525727");
        URLConnection openConnection = url.openConnection();
        InputStream is = openConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("./meinv.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer))!=-1) {
            fos.write(buffer,0,len);
        }
        fos.close();
        is.close();
        System.out.println("读取完毕");
    }
}