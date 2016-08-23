package lab.app_drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FragBMW extends Fragment {

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      String data = getTag();
      View v = inflater.inflate(R.layout.fragment_main, null);
      LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);

      // 採用動態.addView()的方式 若加入比較多項時 就建議採用ListView, GridView的方式 比較節省資源
      for (BMW bmw : UtilBMW.mList) {
         if (bmw.getType().equals(data)) {
            TextView tv = new TextView(getActivity());
            tv.setText(bmw.getName());
            ImageView iv = new ImageView(getActivity());
            Picasso.with(getActivity()).load(bmw.getImageUrl()).into(iv);

            layout.addView(tv);
            layout.addView(iv);
         }
      }
      return v;
   }

}
