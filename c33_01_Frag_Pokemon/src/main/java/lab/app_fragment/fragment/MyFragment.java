package lab.app_fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import lab.app_fragment.R;

public class MyFragment extends Fragment {

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_my, null);
      TextView textView = (TextView) view.findViewById(R.id.textView);
      textView.setText("幸運數字:" + new Random().nextInt(100));
      return view;
   }

}
