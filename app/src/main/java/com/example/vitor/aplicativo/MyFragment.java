package com.example.vitor.aplicativo;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import network.VolleySingleTon;

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
            }}
            RequestQueue requestQueue= VolleySingleTon.getsInstance().getmRequestQueue();




            StringRequest request =new StringRequest(Request.Method.POST,"http://api.olhovivo.sptrans.com.br/v0/Login/Autenticar?token=bc3566315445caf11e148d02dd362a84b3f32fc1adc9ee4e36b2aca411e6cd56",new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getActivity(),"RESPONSE"+response,Toast.LENGTH_SHORT).show();
                }


            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),"ERROR"+error,Toast.LENGTH_SHORT).show();
                }
            }

            );


           // requestQueue.add(request);

        StringRequest request1 =new StringRequest(Request.Method.GET,"http://api.promasters.net.br/cotacao/v1/valores",new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"RESPONSE"+response,Toast.LENGTH_SHORT).show();
            }


        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"ERROR"+error,Toast.LENGTH_SHORT).show();
            }
        }

        ); requestQueue.add(request1);
        return layout;
    }
}
