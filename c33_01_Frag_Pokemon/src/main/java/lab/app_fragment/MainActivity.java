package lab.app_fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lab.app_fragment.fragment.FragNumber;

public class MainActivity extends AppCompatActivity {

   private static int number = 1;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main2);
   }

   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.addButton:
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragNumber fragNumber = new FragNumber();
            ft.add(R.id.container, fragNumber, fragNumber.toString()); //亦可以呼叫ft.replace(.....);
            ft.addToBackStack("test");
            ft.commit();
            break;
         case R.id.backButton:
            getFragmentManager().popBackStack();
            break;
      }
   }

}
