package lab.app_fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import lab.app_fragment.R;

public class FragNumber extends Fragment {

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.frag_number, null);
      TextView textView = (TextView) view.findViewById(R.id.textView);
      textView.setText("幸運數字:" + this.toString());
      return view;
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Toast.makeText(getActivity(), "onCreate", Toast.LENGTH_SHORT).show();
   }

   @Override
   public void onStop() {
      super.onStop();
      Toast.makeText(getActivity(), "onStop", Toast.LENGTH_SHORT).show();
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
      Toast.makeText(getActivity(), "onDestroy", Toast.LENGTH_SHORT).show();
   }

}
