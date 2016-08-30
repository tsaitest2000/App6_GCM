package lab.app_weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

// AppWidgetWeather第三部份
public class AppWidgetWeather extends AppWidgetProvider {

   private Handler mHandler = new Handler();
   private Runnable mRunnable1, mRunnable2;
   public static Weather sWeather;

   @Override
   public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
      mRunnable1 = new Runnable() {
         @Override
         public void run() {
            for (int appWidgetId : appWidgetIds) {
               updateAppWidget(context, appWidgetManager, appWidgetId);
            }
            mHandler.postDelayed(mRunnable1, 1000);
         }
      };

      mRunnable2 = new Runnable() {
         @Override
         public void run() {
            new WeatherThread().start();
            mHandler.postDelayed(mRunnable2, 1000);
         }
      };
      mHandler.post(mRunnable1);
      mHandler.post(mRunnable2);
   }

   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
      CharSequence widgetName = "waiting...";
      CharSequence widgetTemp = "waiting...";
      String widgetIconURL = "";
      final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_weather);

      if (sWeather != null) {
         widgetName = sWeather.getName();
         widgetTemp = String.format("%.2f °C", sWeather.getTemperature());
         widgetIconURL = sWeather.getIcon();

         // Construct the RemoteViews object
         views.setTextViewText(R.id.appwidget_name, widgetName);
         views.setTextViewText(R.id.appwidget_temp, widgetTemp);

         Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               views.setImageViewBitmap(R.id.appwidget_icon, getResizedBitmap(bitmap, 250, 250));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
         };
         Picasso.with(context).load(widgetIconURL).into(target);

         // ★★★★★ 困難============的語法 ===================================================================
         // Retrieve a PendingIntent that will start a new activity, like calling Context.startActivity(Intent).
         PendingIntent pendingIntent =
            PendingIntent.getActivity(context, 101, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

         // Equivalent to calling setOnClickListener(android.view.View.OnClickListener) to launch the provided PendingIntent.
         views.setOnClickPendingIntent(R.id.appwidget_icon, pendingIntent);
      }
      // Instruct the widget manager to update the widget
      appWidgetManager.updateAppWidget(appWidgetId, views);
   }

   @Override
   public void onEnabled(Context context) {
      // Enter relevant functionality for when the first widget is created
   }

   @Override
   public void onDisabled(Context context) {
      mHandler.removeCallbacks(mRunnable1);
      mHandler.removeCallbacks(mRunnable2);
   }

   // 網路上寫好能夠將重新決定Bitmap大小的程式區塊
   public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
      int width = bm.getWidth();
      int height = bm.getHeight();
      float scaleWidth = ((float) newWidth) / width;
      float scaleHeight = ((float) newHeight) / height;
      Matrix matrix = new Matrix(); // Create A Matrix For The Manipulation
      matrix.postScale(scaleWidth, scaleHeight); // Resize the bitmap
      Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false); // "Recreate" The New Bitmap
      bm.recycle();
      return resizedBitmap;
   }

}
