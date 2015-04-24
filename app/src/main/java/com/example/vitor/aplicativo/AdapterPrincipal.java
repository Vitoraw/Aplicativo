package com.example.vitor.aplicativo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vitor on 20/04/2015.
 */
public class AdapterPrincipal extends RecyclerView.Adapter<AdapterPrincipal.ViewHolderPrincipal>{

    private ArrayList<Cambio> listCambio =new ArrayList<>();
    private LayoutInflater layoutInflater;

    public AdapterPrincipal(Context context){
        layoutInflater = LayoutInflater.from(context);

    }
    public void setListCambio(ArrayList<Cambio> listCambio){
        this.listCambio = listCambio;
        notifyItemRangeChanged(0,listCambio.size());
    }

    @Override
    public ViewHolderPrincipal onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = layoutInflater.inflate(R.layout.custom_listacambio,parent,false);
        ViewHolderPrincipal viewHolder = new ViewHolderPrincipal(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderPrincipal holder, int position) {
        Cambio cambio = listCambio.get(position);
        holder.moeda.setText(cambio.getMoeda());
        holder.valor.setText(cambio.getValor());
    }

    @Override
    public int getItemCount() {
        return listCambio.size();
    }

    static class ViewHolderPrincipal extends RecyclerView.ViewHolder{

        private TextView moeda;
        private TextView valor;

        public ViewHolderPrincipal(View itemView) {
            super(itemView);
            moeda = (TextView) itemView.findViewById((R.id.moeda));
            valor = (TextView) itemView.findViewById(R.id.valor);
        }
    }

}
