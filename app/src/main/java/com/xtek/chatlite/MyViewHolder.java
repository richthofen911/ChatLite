package com.xtek.chatlite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by admin on 30/04/15.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //Customized ViewHolder Class，holding all views related to an item

    public TextView tv_username;
    public OnlineUserListAdapter.MyItemClickListener myItemClickListener;

    public MyViewHolder(View rootView, OnlineUserListAdapter.MyItemClickListener listener){
        super(rootView);
        tv_username = (TextView) rootView.findViewById(R.id.tv_aUser);
        this.myItemClickListener = listener;
        rootView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(myItemClickListener != null){
            myItemClickListener.onItemClick(v, getPosition());
        }
    }
}
