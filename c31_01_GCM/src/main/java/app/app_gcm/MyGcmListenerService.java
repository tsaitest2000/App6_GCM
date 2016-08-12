/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.app_gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

public class MyGcmListenerService extends GcmListenerService {

   // 推播訊息接收
   @Override
   public void onMessageReceived(String from, Bundle data) {

      String message = data.getString("message");

      sendNotification(from, message);
   }

   // 建立 Notification
   private void sendNotification(String from, String message) {
      int nid = 102;
      Intent intent = new Intent(this, MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_ONE_SHOT);

      Bitmap pokemon = BitmapFactory.decodeResource(getResources(), R.drawable.pokemon_24);
      NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
      style.bigPicture(pokemon);

      Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
         .setSmallIcon(R.mipmap.ic_launcher)
         .setContentTitle("GCM Message")
         .setContentText(from + " " + message)
         .setAutoCancel(true)
         .setStyle(style)
         .setSound(defaultSoundUri)
         .setContentIntent(pendingIntent);

      NotificationManager notificationManager =
         (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

      notificationManager.notify(nid, notificationBuilder.build());
   }

}

