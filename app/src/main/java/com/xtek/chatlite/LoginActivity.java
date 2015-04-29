package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class LoginActivity extends Activity {

    private EditText usrname_input;
    private EditText passwd_input;
    private ImageView userIcon;
    private Button btn_login;

    private String usrname;
    private String passwd;

    private Intent goToChat;
    private SharedPreferences userInfoPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIcon = (ImageView) findViewById(R.id.usricon);
        usrname_input = (EditText) findViewById(R.id.input_passwd);
        passwd_input = (EditText) findViewById(R.id.messageInput);
        userIcon.setImageResource(R.drawable.pewpewpew);
        btn_login = (Button) findViewById(R.id.btn_login);

        userInfoPrefs = getApplication().getSharedPreferences("UserInfoPrefs", 0);

        if(userInfoPrefs.getString("username", null) != null){
            goToChat = new Intent(LoginActivity.this, ChatActivity.class);
            goToChat.putExtra("username", userInfoPrefs.getString("username", null));
            startActivity(goToChat);
        }
    }

    public void onLoginButtonClick(View view){
        userInfoPrefs.edit().putString("username", usrname_input.getText().toString()).apply();
        goToChat = new Intent(LoginActivity.this, ChatActivity.class);
        goToChat.putExtra("username", usrname_input.getText().toString());
        startActivity(goToChat);
    }


}
