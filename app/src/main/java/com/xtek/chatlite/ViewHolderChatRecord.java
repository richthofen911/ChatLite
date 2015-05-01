package com.xtek.chatlite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by admin on 01/05/15.
 */
public class ViewHolderChatRecord extends RecyclerView.ViewHolder{
    public TextView tv_chat_username;
    public TextView tv_chat_message;

    public ViewHolderChatRecord(View rootView){
        super(rootView);
        tv_chat_username = (TextView) rootView.findViewById(R.id.tv_chat_username);
        tv_chat_message = (TextView) rootView.findViewById(R.id.tv_chat_message);
    }
}
