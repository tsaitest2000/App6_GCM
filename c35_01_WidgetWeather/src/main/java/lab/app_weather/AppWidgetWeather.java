package lab.app_weather;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

import java.util.Random;

public class AppWidgetWeather extends AppWidgetProvider {

   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
      // ======================================================
      CharSequence widgetText = new Random().nextInt(100) + "";
      // Construct the RemoteViews object
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_weather);
      views.setTextViewText(R.id.appwidget_text, widgetText);
      // Instruct the widget manager to update the widget
      appWidgetManager.updateAppWidget(appWidgetId, views);
   }

   // ★★★★ 物件變數mRunnable放在方法外面 ==========================================================
   Runnable mRunnable;
   Handler mHandler = new Handler();

   @Override // ★★ 三個參數由於被使用在方法內部類別中 故須宣告為final
   public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
      mRunnable = new Runnable() {
         @Override
         public void run() {
            for (int appWidgetId : appWidgetIds) {
               updateAppWidget(context, appWidgetManager, appWidgetId);
            }
            mHandler.postDelayed(mRunnable, 1000);
         }
      };
      mHandler.post(mRunnable);
   }

   @Override
   public void onEnabled(Context context) {
      // Enter relevant functionality for when the first widget is created
   }

   @Override
   public void onDisabled(Context context) {
      // Enter relevant functionality for when the last widget is disabled
   }

}

