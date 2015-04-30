package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class LoginActivity extends Activity {

    private EditText usrname_input;
    private EditText passwd_input;
    private ImageView userIcon;
    private String usrname;
    private String passwd;

    private Intent goToOnlineUserList;
    private SharedPreferences userInfoPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIcon = (ImageView) findViewById(R.id.usricon);
        usrname_input = (EditText) findViewById(R.id.input_name);
        passwd_input = (EditText) findViewById(R.id.input_passwd);
        userIcon.setImageResource(R.drawable.pewpewpew);
        Button btn_login = (Button) findViewById(R.id.btn_login);

        userInfoPrefs = getApplication().getSharedPreferences("UserInfoPrefs", 0);

        if(userInfoPrefs.getString("username", null) != null){
            goToOnlineUserList = new Intent(LoginActivity.this, OnlineUserListActivity.class);
            goToOnlineUserList.putExtra("username", userInfoPrefs.getString("username", null));
            startActivity(goToOnlineUserList);
        }
    }

    public void onLoginButtonClick(View view){
        userInfoPrefs.edit().putString("username", usrname_input.getText().toString()).apply();
        goToOnlineUserList = new Intent(LoginActivity.this, OnlineUserListActivity.class);
        goToOnlineUserList.putExtra("username", usrname_input.getText().toString());
        startActivity(goToOnlineUserList);
    }


}
