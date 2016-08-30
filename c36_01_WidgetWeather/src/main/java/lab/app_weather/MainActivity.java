package lab.app_weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

// 負責的部份：此款應用程式的主要入口點：獲取天氣資料，並顯示在主要 UI(activity_main.xml)
public class MainActivity extends AppCompatActivity {

   private String mUrl = "http://api.openweathermap.org/data/2.5/forecast/city?q=taipei&APPID=6eada5ea2bdf83d34d3fc39a33ffb403";
   private TextView mTvName, mTvTemp;
   private ImageView mIvWeather;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mTvName = (TextView) findViewById(R.id.name_text);
      mTvTemp = (TextView) findViewById(R.id.temp_text);
      mIvWeather = (ImageView) findViewById(R.id.imageView);

      new RunWork().start();
   }

   class RunWork extends Thread {

      double temp = 0.0;
      String name = "";
      OkHttpClient client = new OkHttpClient();

      String run(String url) throws IOException {
         Request request = new Request.Builder().url(url).build();
         Response response = client.newCall(request).execute();
         return response.body().string();
      }

      public void run() {
         try {
            String json = run(mUrl);
            temp = new JSONObject(json).getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
            name = new JSONObject(json).getJSONObject("city").getString("name");
            temp -= 273.15;
            runOnUiThread(runnable);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            mTvTemp.setText(String.format("%.2f °C", temp));
            mTvName.setText(name);
         }
      };
   }

}
