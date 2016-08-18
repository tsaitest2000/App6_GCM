package com.gcm.student.myapplication.backend;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GcmSender {

   public static final String API_KEY = "AIzaSyDP09QRK9p2obyuvgRPXisJkdnc1ClfBBU";
   public static String to = "/topics/global";

   // static的main方法中呼叫同為static的send(String title, String message) =================
   public static void main(String[] args) throws Exception {
      send("AAA", "BBB");
   }

   // 新增這個static方法============================
   public static void send(String title, String message) throws JSONException, MalformedURLException, IOException {
      JSONObject jGcmData = new JSONObject();
      JSONObject jData = new JSONObject();
      // 參數改成 title =========================
      jData.put("title", title);
      // 參數改成 message =======================
      jData.put("message", message);
      jGcmData.put("to", to);
      jGcmData.put("data", jData);

      URL url = new URL("https://android.googleapis.com/gcm/send");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestProperty("Authorization", "key=" + API_KEY);
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);

      OutputStream outputStream = conn.getOutputStream();
      outputStream.write(jGcmData.toString().getBytes());

      InputStream inputStream = conn.getInputStream();
      String resp = IOUtils.toString(inputStream);
      System.err.println(resp);
      System.err.println("傳送成功，請檢查手機觀察是否有推播訊息進入!");
   }

}
