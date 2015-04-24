package com.example.vitor.aplicativo;


import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

import network.VolleySingleTon;
import static com.example.vitor.aplicativo.Keys.EndpointPrincipal.*;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Principal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Principal extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;
    private static final String URL_CAMBIO = "http://api.promasters.net.br/cotacao/v1/valores";
    private ArrayList<Cambio> listacambio = new ArrayList<>();
    private RecyclerView listCambio;
    private AdapterPrincipal adapterPrincipal;
    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Principal.
     */
    // TODO: Rename and change types and number of parameters
    public static Principal newInstance(String param1, String param2) {
        Principal fragment = new Principal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Principal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();
        sendJsonRequest();
    }
     private void sendJsonRequest(){

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,URL_CAMBIO,(String) null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

           listacambio= parseJSONResponse(response);
           adapterPrincipal.setListCambio(listacambio);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }

    private ArrayList<Cambio> parseJSONResponse(JSONObject response) {
        ArrayList<Cambio> listacambio = new ArrayList<>();
        if(response!= null && response.length()>0){




        StringBuilder data=new StringBuilder();
        try{

        JSONObject valores = response.getJSONObject(KEY_TITULO);


        for(int i=0;i<3;i++) {
            JSONObject moeda = valores.getJSONObject(KEY_MOEDAS[i]);
            String nomemoeda = null;
            String valor=null;
            if (moeda.has(KEY_NOME)) {

                nomemoeda = moeda.getString(KEY_NOME);
                valor = moeda.getString(KEY_VALOR);
            }

            Cambio cambio =new Cambio();
            cambio.setMoeda(nomemoeda);
            cambio.setValor(valor);

            listacambio.add(cambio);
        }
        }catch(JSONException e){

        }}
        return listacambio;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeprincipal);
        swipeRefreshLayout.setOnRefreshListener(this);
        listCambio = (RecyclerView) view.findViewById(R.id.cambiolist);

        listCambio.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterPrincipal=new AdapterPrincipal(getActivity());
        listCambio.setAdapter(adapterPrincipal);
        sendJsonRequest();
        return view;

    }


    @Override
    public void onRefresh() {
        L.t(getActivity(),swipeRefreshLayout.isRefreshing());
        sendJsonRequest();
        swipeRefreshLayout.setRefreshing(false);




    }
}
