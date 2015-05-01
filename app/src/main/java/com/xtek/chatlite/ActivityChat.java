package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityChat extends Activity {

    private Intent intent;

    private RecyclerView recyclerView_chatRecord;
    private ArrayList<ChatRecord> chatRecords = new ArrayList<>();
    private AdapterChatRecord adapterChatRecord;

    private static final String MY_MAIN_URL = "https://chatlitegalaxy.firebaseio.com/";
    private static String MAIN_URL_PREFIX = "https://chatlite";
    private static String MAIN_URL_SUFFIX = ".firebaseio.com/";
    private static String TARGET_MAIN_URL;
    private Firebase ref_myMain = new Firebase(MY_MAIN_URL);
    private Firebase ref_targetMain;
    private ImageButton btn_send;
    private EditText et_messageInput;
    private String mUsername = User.getUserName();

    private Map<String, Object> newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btn_send = (ImageButton)findViewById(R.id.btn_send);
        et_messageInput = (EditText)findViewById(R.id.et_messageInput);

        intent = this.getIntent();

        TARGET_MAIN_URL = MAIN_URL_PREFIX + intent.getStringExtra("targetName") + MAIN_URL_SUFFIX;
        setupUsername();

        recyclerView_chatRecord = (RecyclerView) findViewById(R.id.recyclerview_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_chatRecord.setLayoutManager(linearLayoutManager);
        recyclerView_chatRecord.setHasFixedSize(true);
        adapterChatRecord = new AdapterChatRecord(chatRecords);
        recyclerView_chatRecord.setAdapter(adapterChatRecord);

        ref_targetMain = new Firebase(TARGET_MAIN_URL);

        ref_targetMain.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newPost = (Map<String, Object>) dataSnapshot.getValue();
                chatRecords.add(new ChatRecord((String) newPost.get("username"), (String) newPost.get("message")));
                System.out.println("new record username: " + newPost.get("username") + newPost.get("message"));
                adapterChatRecord.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //System.out.println("The updated online user list is " + dataSnapshot.child("OnlineUsers").getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void setupUsername() {
        mUsername = User.getUserName();
    }

    public void onSendClick(View view){
        Map<String, String> newChatRecord = new HashMap<>();
        newChatRecord.put("username", mUsername);
        newChatRecord.put("message", et_messageInput.getText().toString());
        ref_targetMain.push().setValue(newChatRecord);
        //chatRecords.add(new ChatRecord(mUsername + ": ", et_messageInput.getText().toString()));
        et_messageInput.setText("");
        adapterChatRecord.notifyDataSetChanged();
    }

}
