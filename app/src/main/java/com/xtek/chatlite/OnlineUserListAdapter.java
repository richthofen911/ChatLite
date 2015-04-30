package com.xtek.chatlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OnlineUserListAdapter extends RecyclerView.Adapter<MyViewHolder>{

    MyItemClickListener myItemClickListener;

    public ArrayList<String> onlineUsers = new ArrayList<>();

    public OnlineUserListAdapter(ArrayList<String> data){
        onlineUsers = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_user,viewGroup,false);
        //ViewHolder vh = new ViewHolder(view);
        return new MyViewHolder(view, myItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.tv_username.setText(onlineUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return onlineUsers.size();
    }

    public interface MyItemClickListener{
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }
}
