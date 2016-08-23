package lab.app_drawer;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UtilBMW {

   public static List<BMW> mList;

   public static Map<String, String> readRawTextFile1(Context context, int resId) {
      Map<String, String> map = new TreeMap<>();
      InputStream inputStream = context.getResources().openRawResource(resId);
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      try {
         while ((line = bufferedReader.readLine()) != null) {
            map.put(line.split(",")[1], line.split(",")[2]);
         }
      } catch (IOException e) {
         return null;
      }
      return map;
   }

   public static List<BMW> readRawTextFile2(Context context, int resId) {
      List<BMW> list = new ArrayList<>();
      InputStream inputStream = context.getResources().openRawResource(resId);
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      try {
         while ((line = bufferedReader.readLine()) != null) {
            list.add(new BMW(line.split(",")[0], line.split(",")[1], line.split(",")[2]));
         }
      } catch (IOException e) {
         return null;
      }
      return list;
   }

}
