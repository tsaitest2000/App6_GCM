package app.app_gcm;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   private Context context;
   private TextView textView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      textView = (TextView) findViewById(R.id.textView);
      context = this;
      new RunWork().start();
   }

   class RunWork extends Thread {
      // 授權 token
      String token = "";

      // 訂閱主題
      String[] TOPICS = {"global"};

      Runnable r = new Runnable() {
         @Override
         public void run() {
            textView.setText(token);
         }
      };

      // 取得授權 token
      @Override
      public void run() {
         InstanceID instanceID = InstanceID.getInstance(context);
         try {
            // 授權 token
            token = instanceID.getToken("492200446996", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            System.out.println("token=" + token);
            // 設定訂閱
            subscribeTopics(token);
         } catch (IOException e) {
            e.printStackTrace();
            token = e.toString();
         }
         runOnUiThread(r);
      }

      // 設定訂閱
      private void subscribeTopics(String token) throws IOException {
         GcmPubSub pubSub = GcmPubSub.getInstance(context);
         for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
         }
      }
   }

}

