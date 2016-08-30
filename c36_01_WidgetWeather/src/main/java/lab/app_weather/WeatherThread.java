package lab.app_weather;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

// AppWidgetWeather第二部份：負責到網路獲取即時的天氣資料 → 放入到第三方(Weather型別物件) → AppWidgetWeather來取用
public class WeatherThread extends Thread {

   private String mUrl = "http://api.openweathermap.org/data/2.5/forecast/city?APPID=6eada5ea2bdf83d34d3fc39a33ffb403&q=taipei";

   OkHttpClient client = new OkHttpClient();

   String run(String url) throws IOException {
      Request request = new Request.Builder().url(url).build();
      Response response = client.newCall(request).execute();
      return response.body().string();
   }

   public void run() {
      try {
         // WeatherThread到網路獲取即時的天氣資料
         String json = run(mUrl);
         JSONObject jsonObject = new JSONObject(json);

         String name = jsonObject.getJSONObject("city").getString("name");
         double temperature = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
         String icon = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon");
         temperature -= 273.15;

         // 將即時的天氣資料放入到第三方(Weather型別物件中)
         Weather weather = new Weather();
         weather.setName(name);
         weather.setTemperature(temperature);
         weather.setIcon(String.format("http://openweathermap.org/img/w/%s.png", icon));
         System.out.println(temperature); // Debug之用：觀察是否有在下載天氣資料...

         // AppWidgetWeather自行到第三方(Weather型別物件)中取用即時的天氣資料
         AppWidgetWeather.sWeather = weather;
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
