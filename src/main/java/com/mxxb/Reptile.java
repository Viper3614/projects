package com.mxxb;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

/**
 * @author Viper
 * @description
 * @date 2021/3/19
 */


public class Reptile {
    private static final Log log = LogFactory.getLog(Reptile.class);
    private static String videoSavePath = "F:/music";


    public static void main(String[] args) {
        String searchName = "";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            searchName = scanner.next();
            String address = "https://songsearch.kugou.com/song_search_v2?keyword=" + searchName + "&platform=WebFilter";

            String jsonStr = HttpUtil.createGet(address).execute().body();
            List<HttpCookie> cookies = HttpUtil.createGet(address).execute().getCookies();

            JSONObject json = JSONObject.parseObject(jsonStr);

            String fileHash = json.getJSONObject("data").getJSONArray("lists").getJSONObject(0).getString("FileHash"); //3BD5C05B9F8D082BA3C9425A1A712394
            String audioName = json.getJSONObject("data").getJSONArray("lists").getJSONObject(0).getString("FileName"); //周杰伦 - 晴天

            String songUrl = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash=" + fileHash + "&mid=1";

            String jsonStr1 = HttpUtil.createGet(songUrl).execute().body();
            JSONObject json1 = JSONObject.parseObject(jsonStr1);
            String playUrl = json1.getJSONObject("data").getJSONObject("data").getString("play_url");
            if (playUrl == null) {
                log.warn("playUrl 为空,"+playUrl);
            }
            log.info(searchName + ",下载地址为 ：" + playUrl);

            downloadMusic(playUrl, audioName, "酷狗");
        }
    }

    public static void downloadMusic(String httpUrl, String title, String source) {
        String fileAddress = videoSavePath + "/" + source + "/" + title + ".mp4";
        int len;
        try {
            URL url = new URL(httpUrl);
            URLConnection openConnection = url.openConnection();

            InputStream is = openConnection.getInputStream();

            FileOutputStream fos = new FileOutputStream(new File(fileAddress));
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
            log.info(title + ",下载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}