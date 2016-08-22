package com.example.kevin.testamazingbabe;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kevin.testamazingbabe.fragments.FragPirate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private Button mBtnAdd, mBtnDelete;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mBtnAdd = (Button) this.findViewById(R.id.btnAdd);
      mBtnDelete = (Button) this.findViewById(R.id.btnDelete);
      mBtnAdd.setOnClickListener(this);
      mBtnDelete.setOnClickListener(this);
   }

   @Override
   public void onClick(View view) {
      switch (view.getId()) {
         case R.id.btnAdd:
            FragmentTransaction ft = MainActivity.this.getFragmentManager().beginTransaction();
            ft.add(R.id.linearLayout, new FragPirate());
            ft.addToBackStack("Babe");
            ft.commit();
            break;
         case R.id.btnDelete:
            MainActivity.this.getFragmentManager().popBackStack();
            break;
      }
   }

}
