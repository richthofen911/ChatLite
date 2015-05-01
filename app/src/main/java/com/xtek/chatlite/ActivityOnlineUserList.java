package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityOnlineUserList extends Activity implements AdapterOnlineUserList.MyItemClickListener{

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://chatlite.firebaseio.com/";
    private static final String ONLINEUSER_URL = "https://chatlite.firebaseio.com/OnlineUsers";
    public static String nameForUrl;

    public static String mUsername;
    private Firebase rootRef;
    private Firebase onlineUserRef;
    private ValueEventListener mConnectedListener;
    private AdapterOnlineUserList mAdapterOnlineUserList;
    private Intent intent;
    private Intent goToChat;

    private RecyclerView mRecyclerView;
    private ArrayList<String> onlineUsers = new ArrayList<>();
    private AdapterOnlineUserList adapterOnlineUserList;

    private Map<String, Object> newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineuser_list);
        intent = this.getIntent();
        setupUsername();
        setTitle("Current Online Users: ");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_onlineuserlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        adapterOnlineUserList = new AdapterOnlineUserList(onlineUsers);
        mRecyclerView.setAdapter(adapterOnlineUserList);
        adapterOnlineUserList.setOnItemClickListener(this);
        // Setup Firebase ref
        rootRef = new Firebase(FIREBASE_URL);
        onlineUserRef = new Firebase(ONLINEUSER_URL);
        Map<String, String> myInfo = new HashMap<String, String>();
        myInfo.put("username", mUsername);
        onlineUserRef.child(mUsername).setValue(myInfo);

        onlineUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //System.out.println("data snapshot" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //System.out.println("Read firebase failed: " + firebaseError);
            }
        });

        onlineUserRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                newPost = (Map<String, Object>) dataSnapshot.getValue();
                onlineUsers.add((String) newPost.get("username"));
                System.out.println("Come User: " + newPost.get("username"));
                adapterOnlineUserList.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //System.out.println("The updated online user list is " + dataSnapshot.child("OnlineUsers").getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                for(int i = 0; i < onlineUsers.size(); i++){
                    if(onlineUsers.get(i).equals(newPost.get("username"))){
                        onlineUsers.remove(i);
                        adapterOnlineUserList.notifyDataSetChanged();
                    }
                }
                System.out.println("Leave User: " + newPost.get("username"));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

/*
        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
*/
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        //mOnlineUserListAdapter = new OnlineUserListAdapter(onlineUserRef, this, R.layout.activity_onlineuser_list, mUsername);
        listView.setAdapter(mOnlineUserListAdapter);
        mOnlineUserListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mOnlineUserListAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = rootRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(ChatActivity.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });
    }
*/
/*
    @Override
    public void onStop() {
        super.onStop();
        rootRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mOnlineUserListAdapter.cleanup();
    }
*/
    @Override
    public void onItemClick(View view, int position){

        goToChat = new Intent(ActivityOnlineUserList.this, ActivityChat.class);
        goToChat.putExtra("targetName", nameForUrl);
        startActivity(goToChat);

    }

    private void setupUsername() {
        User.setUserName(intent.getStringExtra("username"));
        mUsername = User.getUserName();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        rootRef.child("OnlineUsers").child(mUsername).removeValue();
        Intent quitApp = new Intent();
        quitApp.setClass(ActivityOnlineUserList.this, ActivityLogin.class);
        quitApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(quitApp);
    }
}
