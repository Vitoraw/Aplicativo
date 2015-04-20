package com.example.vitor.aplicativo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by z003fdja on 19/04/2015.
 */
public class MyFragment extends Fragment{
    private TextView textView;

    public static MyFragment getInstance (int position){
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.teste,container,false);
        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            if(bundle.getInt("position")==1){
                layout = inflater.inflate(R.layout.teste2,container,false);
            }

        }
        return layout;
    }
}
