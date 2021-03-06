package com.gb.base_1919_social.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gb.base_1919_social.R;
import com.gb.base_1919_social.repository.PostData;
import com.gb.base_1919_social.repository.PostsSource;

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkAdapter.MyViewHolder> {

    private PostsSource postsSource;

    OnItemClickListener onItemClickListener;

    Fragment fragment;

    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setData(PostsSource postsSource) {
        this.postsSource = postsSource;
        notifyDataSetChanged(); // команда адаптеру отрисовать все(!) полученные данные
    }

    SocialNetworkAdapter(PostsSource postsSource){
        this.postsSource = postsSource;
    }
    SocialNetworkAdapter(){
    }
    SocialNetworkAdapter(Fragment fragment){
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_social_network_cardview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(postsSource.getCardData(position));
    }

    @Override
    public int getItemCount() {
        return postsSource.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final ImageView imageView;
        private final ToggleButton like;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            like = (ToggleButton) itemView.findViewById(R.id.like);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    //view.showContextMenu(0,0);
                    return false;
                }
            });
            fragment.registerForContextMenu(itemView);
            /*textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });*/
        }
            // связываем контент с макетом
        public void bindContentWithLayout(PostData content){

            textViewTitle.setText(content.getTitle());
            textViewDescription.setText(content.getDescription()+" "+content.getDate());
            imageView.setImageResource(content.getPicture());
            like.setChecked(content.isLike());
        }

    }

}
