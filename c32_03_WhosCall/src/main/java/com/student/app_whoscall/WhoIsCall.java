package com.student.app_whoscall;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public class WhoIsCall extends BroadcastReceiver {

   @Override
   public void onReceive(Context context, Intent intent) {
      String phone = intent.getStringExtra("incoming_number");
      System.out.println("onReceiver : " + phone);
      if (phone != null && phone.indexOf("+") != -1) {
         createNotification(context, phone);
      }
   }

   private void createNotification(Context context, String phone) {

      NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      Notification.Builder builder = new Notification.Builder(context)
         .setSmallIcon(R.mipmap.ic_launcher)
         .setContentTitle("詐騙電話")
         .setContentText(phone);
      manager.notify(101, builder.build());

      try {
         endCall(context);
      } catch (Exception e) {
         e.printStackTrace(System.out);
      }
   }

   // 掛斷電話
   private void endCall(Context context) throws Exception {
      TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

      Method m1 = tm.getClass().getDeclaredMethod("getITelephony");
      m1.setAccessible(true);
      Object iTelephony = m1.invoke(tm);

      Method m2 = iTelephony.getClass().getDeclaredMethod("silenceRinger");
      Method m3 = iTelephony.getClass().getDeclaredMethod("endCall");

      m2.invoke(iTelephony);
      m3.invoke(iTelephony);
   }

}