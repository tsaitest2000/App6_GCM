package lab.app_fragment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import lab.app_fragment.R;
import pokemon.Pokemon;
import pokemon.PokemonUtil;

public class FragPokemon extends Fragment {

   private TextView mTvPokemon;
   private ImageView mIvPokemon;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.frag_pokemon, null);
      mTvPokemon = (TextView) view.findViewById(R.id.text_view_pokemon);
      mIvPokemon = (ImageView) view.findViewById(R.id.image_view_pokemon);
      return view;
   }

   @Override
   public void onResume() {
      super.onResume();
      new RunWork().start();
   }

   class RunWork extends Thread {

      private List<Pokemon> list;

      public void run() {
         try {
            list = PokemonUtil.getPokemon();
            getActivity().runOnUiThread(runnable);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      Runnable runnable = new Runnable() {
         @Override
         public void run() {
            int number = new Random().nextInt(list.size());
            Picasso.with(getActivity()).load(list.get(number).getImage()).into(mIvPokemon);
            mTvPokemon.setText(list.get(number).getName());
         }
      };
   }

}
