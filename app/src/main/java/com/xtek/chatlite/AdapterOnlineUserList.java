package com.xtek.chatlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterOnlineUserList extends RecyclerView.Adapter<ViewHolderOnlineUserList>{

    MyItemClickListener myItemClickListener;

    public ArrayList<User> onlineUsers = new ArrayList<>();

    public AdapterOnlineUserList(ArrayList<User> data){
        onlineUsers = data;
    }

    @Override
    public ViewHolderOnlineUserList onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_user,viewGroup,false);
        //ViewHolder vh = new ViewHolder(view);
        return new ViewHolderOnlineUserList(view, myItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderOnlineUserList viewHolder, int position) {
        viewHolder.tv_username.setText(onlineUsers.get(position).getUserName());
        viewHolder.tv_username.setHint(onlineUsers.get(position).getUserEmail());
    }

    @Override
    public int getItemCount() {
        return onlineUsers.size();
    }

    public interface MyItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }
}
