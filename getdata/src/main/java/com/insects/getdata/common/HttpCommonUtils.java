package com.insects.getdata.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpCommonUtils {

    public static void setHeader(HttpPost httpPost) {
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpPost.setHeader("Cache-Control", "max-age=0");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"); //这项内容很重要
    }

    public static JSONArray getJSONArrayResponse(HttpResponse response){
        String result = null;
        JSONArray jsonArray = null;
        try {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonArray = JSONObject.parseArray(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject getJSONObjectResponse(HttpResponse response){
        String result = null;
        JSONObject jsonObject = null;
        try {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
