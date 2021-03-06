package com.example.vitor.aplicativo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vitor on 18/04/2015.
 */
public class VitorAdapter extends RecyclerView.Adapter<VitorAdapter.MyViewHolder> {
   private LayoutInflater inflater;
    private Context context;
    private ClickListerner clickListerner;
    List<Information> data = Collections.emptyList();

    public VitorAdapter(Context context, List<Information> data){
       this.context = context;
        inflater  = LayoutInflater.from(context);
       this.data= data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current=data.get(position);

        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

    }
    public void setClickListerner(ClickListerner clickListerner){
        this.clickListerner=clickListerner;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            if (clickListerner!=null){
                clickListerner.itemClicked(v,getPosition());
            }
        }
    }
    public interface ClickListerner{
        public void itemClicked(View view, int position);
    }
}
