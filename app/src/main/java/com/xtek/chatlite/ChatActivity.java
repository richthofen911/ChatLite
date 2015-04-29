package com.xtek.chatlite;

import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends ListActivity {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://chatlite.firebaseio.com/";
    private static final String ONLINEUSER_URL = "https://chatlite.firebaseio.com/OnlineUsers";


    private String mUsername;
    private Firebase rootRef;
    private Firebase onlineUserRef;
    private ValueEventListener mConnectedListener;
    private OnlineUserListAdapter mOnlineUserListAdapter;
    private Intent intent;
    private User me;
    private String mTempID;

    private Map<String, Object> newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        intent = this.getIntent();
        // Make sure we have a mUsername
        setupUsername();

        setTitle("Chatting as " + mUsername);

        // Setup our Firebase ref
        rootRef = new Firebase(FIREBASE_URL);
        onlineUserRef = new Firebase(ONLINEUSER_URL).push();
        //onlineUserRef = new Firebase(ONLINEUSER_URL);
        Map<String, String> myInfo = new HashMap<String, String>();
        myInfo.put("username", mUsername);
        onlineUserRef.setValue(myInfo);
        mTempID = onlineUserRef.getKey();
        System.out.println("push location unique id: " + mTempID);
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
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                System.out.println("Come User: " + newPost.get("username"));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //System.out.println("The updated online user list is " + dataSnapshot.child("OnlineUsers").getValue());


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                System.out.println("Leave User: " + newPost.get("username"));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

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

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        mOnlineUserListAdapter = new OnlineUserListAdapter(onlineUserRef, this, R.layout.online_user_list, mUsername);
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
    private void setupUsername() {
        me = new User(intent.getStringExtra("username"));
        mUsername = me.getUserName();
    }

    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, mUsername);
            // Create a new, auto-generated child of that chat location, and save our chat data there
            rootRef.child("Chat").child(mUsername).setValue(chat.getMessage());
            inputText.setText("");
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        rootRef.child("OnlineUsers").child(mTempID).removeValue();
    }
}
