package com.example.testfragdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FragPirate extends Fragment {

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.frag_pirate, null);
      LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);

      return super.onCreateView(inflater, container, savedInstanceState);
   }

}
