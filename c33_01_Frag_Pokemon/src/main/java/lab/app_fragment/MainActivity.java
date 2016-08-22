package lab.app_fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lab.app_fragment.fragment.PokemonFrag;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main2);
   }

   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.addButton:
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container, new PokemonFrag()); //亦可以呼叫ft.replace(.....);
            ft.addToBackStack("test");
            ft.commit();
            break;
         case R.id.backButton:
            getFragmentManager().popBackStack();
            break;
      }
   }

}
