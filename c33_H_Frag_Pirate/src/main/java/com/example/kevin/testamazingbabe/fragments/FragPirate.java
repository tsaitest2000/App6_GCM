package com.example.kevin.testamazingbabe.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.testamazingbabe.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FragPirate extends Fragment {

   private ImageView mIvPirate;
   private TextView mTvPirate, mTvBirth, mTvFruit, mTvFight, mTvTeam;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.frag_pirate, null);
      mIvPirate = (ImageView) view.findViewById(R.id.imageView);
      mTvPirate = (TextView) view.findViewById(R.id.textView);
      mTvBirth = (TextView) view.findViewById(R.id.tvBirth);
      mTvFruit = (TextView) view.findViewById(R.id.tvFruit);
      mTvFight = (TextView) view.findViewById(R.id.tvFight);
      mTvTeam = (TextView) view.findViewById(R.id.tvTeam);
      return view;
   }

   @Override
   public void onResume() {
      super.onResume();
      new RunWork().start();
   }

   private class RunWork extends Thread {

      @Override
      public void run() {
         try {
            Document doc = Jsoup.connect("https://zh.wikipedia.org/wiki/%E8%92%99%E5%85%B6%C2%B7D%C2%B7%E9%AD%AF%E5%A4%AB").get();
            Elements tables = doc.select("table");
            final Element table = tables.get(1);

            getActivity().runOnUiThread(new Runnable() {
               @Override
               public void run() {
                  Picasso
                     .with(getActivity())
                     .load(table.select("tr").get(2).select("td").get(0).select("img").first().absUrl("src"))
                     .into(mIvPirate);
                  mTvPirate.setText(table.select("tr").get(0).select("th").get(0).text());
                  mTvBirth.setText(table.select("tr").get(10).select("td").first().text().substring(0, 4));
                  mTvFruit.setText(table.select("tr").get(17).select("td").first().text());
                  mTvFight.setText(table.select("tr").get(18).select("td").first().text());
                  mTvTeam.setText(table.select("tr").get(20).select("td").first().text());
               }
            });
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

}
