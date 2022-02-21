package com.gb.base_1919_social;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkAdapter.MyViewHolder> {

    private String[] data;

    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged(); // команда адаптеру отрисовать все(!) полученные данные
    }

    SocialNetworkAdapter(String[] data){
        this.data = data;
    }
    SocialNetworkAdapter(){
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_social_network_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
            // связываем контент с макетом
        public void bindContentWithLayout(String content){
            textView.setText(content);
        }

    }

}
