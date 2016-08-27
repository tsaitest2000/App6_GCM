package lab.app_weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   private TextView mTvCity, mTvTemp;
   private ImageView mIvPicture;
   private String m_City;
   private String m_City_Data;
   private String m_City_Url;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mTvCity = (TextView) findViewById(R.id.text_view_city);
      mTvTemp = (TextView) findViewById(R.id.text_view_temperature);
      mIvPicture = (ImageView) findViewById(R.id.image_view_picture);
      m_City_Data = "http://api.openweathermap.org/data/2.5/forecast/city?APPID=6eada5ea2bdf83d34d3fc39a33ffb403&q=%s";
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(0, 1, Menu.NONE, "台北市");
      menu.add(0, 2, Menu.NONE, "新北市");
      menu.add(0, 3, Menu.NONE, "台中市");
      menu.add(0, 4, Menu.NONE, "台南市");
      menu.add(0, 5, Menu.NONE, "高雄市");
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case 1:
            m_City = "Taipei";
            break;
         case 2:
            m_City = "NewTaipei";
            break;
         case 3:
            m_City = "Taichung";
            break;
         case 4:
            m_City = "Tainan";
            break;
         case 5:
            m_City = "Kaohsiung";
            break;
      }
      m_City_Url = String.format(m_City_Data, m_City);
      new RunWork_Weather().start();
      return super.onOptionsItemSelected(item);
   }

   class RunWork_Weather extends Thread {

      private String strJson;
      private String city;
      private String condition;
      private double temperature = 0.0;

      OkHttpClient client = new OkHttpClient();

      String run(String url) throws IOException {
         Request request = new Request.Builder().url(url).build();
         Response response = client.newCall(request).execute();
         return response.body().string();
      }

      public void run() {
         try {
            strJson = run(m_City_Url);
            JSONObject jsonObject = new JSONObject(strJson);
            city = jsonObject.getJSONObject("city").getString("name");
            temperature = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
            temperature -= 273.15;
            condition = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
            runOnUiThread(mRunnable);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      Runnable mRunnable = new Runnable() {
         @Override
         public void run() {
            mTvTemp.setText(String.format("%.2f °C", temperature));
            mTvCity.setText(city);
            switch (condition) {
               case "Sun":
                  mIvPicture.setImageResource(R.drawable.weather_sun);
                  break;
               case "Clouds":
                  mIvPicture.setImageResource(R.drawable.weather_clouds);
                  break;
               case "Rain":
                  mIvPicture.setImageResource(R.drawable.weather_rain);
                  break;
            }
         }
      };
   }

}
