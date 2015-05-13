package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    public static String emailForTargetUrl;

    public String mUsername;
    public String mEmail;
    private Firebase rootRef;
    private Firebase onlineUserRef;
    private Intent intent;
    private Intent goToChat;

    private RecyclerView mRecyclerView;
    private ArrayList<User> onlineUsers = new ArrayList<>();
    private AdapterOnlineUserList adapterOnlineUserList;

    private Map<String, Object> newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineuser_list);
        intent = this.getIntent();
        setupUsername();
        setTitle("Current Online Users: ");

        // setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_onlineuserlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        adapterOnlineUserList = new AdapterOnlineUserList(onlineUsers);
        mRecyclerView.setAdapter(adapterOnlineUserList);
        adapterOnlineUserList.setOnItemClickListener(this);

        // setup Firebase ref
        rootRef = new Firebase(FIREBASE_URL);
        onlineUserRef = new Firebase(ONLINEUSER_URL);
        Map<String, String> myInfo = new HashMap<String, String>();
        myInfo.put("email", mEmail);
        myInfo.put("username", mUsername);
        onlineUserRef.child("clqq" + mEmail.substring(2, 7)).setValue(myInfo);

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
                onlineUsers.add(new User((String) newPost.get("username"), (String) newPost.get("email")));
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
    }

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
        goToChat.putExtra("targetEmail", emailForTargetUrl);
        startActivity(goToChat);

    }

    private void setupUsername() {
        mUsername = Me.getUserName();
        mEmail = Me.getUserEmail();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("remove user: " + rootRef.child("OnlineUsers").child("clqq" + mEmail.substring(2, 7)));
        rootRef.child("OnlineUsers").child("clqq" + mEmail.substring(2, 7)).removeValue();
        //Intent quitApp = new Intent();
        //quitApp.setClass(ActivityOnlineUserList.this, ActivityLogin.class);
        //quitApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(quitApp);
    }
}
