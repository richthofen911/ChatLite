package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityChat extends Activity {

    private Intent intent;
    User me;
    String mUsername;

    private RecyclerView recyclerView_chatRecord;
    private ArrayList<ChatRecord> chatRecords = new ArrayList<>();
    private AdapterChatRecord adapterChatRecord;

    private static final String CHAT_URL = "https://chatlitechat.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        intent = this.getIntent();
        setupUsername();

        recyclerView_chatRecord = (RecyclerView) findViewById(R.id.recyclerview_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_chatRecord.setLayoutManager(linearLayoutManager);
        recyclerView_chatRecord.setHasFixedSize(true);
        adapterChatRecord = new AdapterChatRecord(chatRecords);
        recyclerView_chatRecord.setAdapter(adapterChatRecord);
        
    }

    private void setupUsername() {
        me = new User(intent.getStringExtra("username"));
        mUsername = me.getUserName();
    }
}
