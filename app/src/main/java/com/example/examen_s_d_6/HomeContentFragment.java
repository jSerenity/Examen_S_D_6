package com.example.examen_s_d_6;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeContentFragment extends Fragment {

  private static final String TEXT = "text";

  public static HomeContentFragment newInstance(String text) {
    HomeContentFragment frag = new HomeContentFragment();

    Bundle args = new Bundle();
    args.putString(TEXT, text);
    frag.setArguments(args);

    return frag;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
          Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.home_fragment, container, false);

    if (getArguments() != null) {
      //((TextView) layout.findViewById(R.id.text)).setText(getArguments().getString(TEXT));
    }
    ImageView button = (ImageView) layout.findViewById(R.id.desa);
    button.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Toast.makeText(v.getContext(), getString(R.string.menu_desa),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), VerProductosActivity.class);
        intent.putExtra("VIEW_NAME", 1);
        startActivity(intent);
      }
    });
    ImageView buttonalmu = (ImageView) layout.findViewById(R.id.almuer);
    buttonalmu.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Toast.makeText(v.getContext(), getString(R.string.menu_almu),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), VerProductosActivity.class);
        intent.putExtra("VIEW_NAME", 2);
        startActivity(intent);
      }
    });
    ImageView buttoncena = (ImageView) layout.findViewById(R.id.cena);
    buttoncena.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Toast.makeText(v.getContext(), getString(R.string.menu_cena),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), VerProductosActivity.class);
        intent.putExtra("VIEW_NAME", 3);
        startActivity(intent);
      }
    });
    ImageView buttonpos = (ImageView) layout.findViewById(R.id.postres);
    buttonpos.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Toast.makeText(v.getContext(), getString(R.string.menu_postres),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), VerProductosActivity.class);
        intent.putExtra("VIEW_NAME", 4);
        startActivity(intent);
      }
    });

    return layout;
  }

  /*public void onClick(View view){
    int title;
    Fragment fragment;
    switch (view.getId()) {
      case R.id.desa:
        title = R.string.menu_home;
        fragment = desayunosFragment.newInstance(getString(title),"");
        break;
      case R.id.nav_pedidos:
        title = R.string.menu_pedidos;
        fragment = pedidiosFragment.newInstance(getString(title),"");
        break;
      case R.id.nav_sucursales:
        title = R.string.menu_sucursales;
        fragment = HomeContentFragment.newInstance(getString(title));
        break;
      default:
        throw new IllegalArgumentException("menu option not implemented!!");
    }


    getActivity().getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
            .replace(R.id.home_content, fragment, "findThisFragment")
            .addToBackStack(null)
            .commit();*/
    /*getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
            .replace(R.id.home_content, fragment)
            .commit();

  }*/
}

