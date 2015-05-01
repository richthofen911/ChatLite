package com.xtek.chatlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterChatRecord extends RecyclerView.Adapter<ViewHolderChatRecord>{

    public ArrayList<ChatRecord> chatRecords = new ArrayList<>();

    public AdapterChatRecord(ArrayList<ChatRecord> data){
        chatRecords = data;
    }

    @Override
    public ViewHolderChatRecord onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_message,viewGroup,false);
        return new ViewHolderChatRecord(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderChatRecord viewHolder, int position) {
        viewHolder.tv_chat_username.setText(chatRecords.get(position).getUsername());
        viewHolder.tv_chat_message.setText(chatRecords.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return chatRecords.size();
    }
}
