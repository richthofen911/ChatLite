package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityLogin extends Activity {

    private EditText et_email;
    private EditText usrname_input;
    private EditText passwd_input;
    private ImageView userIcon;
    private String usrname;
    private String passwd;

    private Intent goToOnlineUserList;
    private SharedPreferences userInfoPrefs;

    private static final String MAIN_URL_PREFIX = "https://chatlite";
    private static final String MAIN_URL_SUFFIX = ".firebaseio.com/";
    private String MY_MAIN_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIcon = (ImageView) findViewById(R.id.usricon);
        et_email = (EditText) findViewById(R.id.et_messageInput);
        usrname_input = (EditText) findViewById(R.id.input_name);
        userIcon.setImageResource(R.drawable.pewpewpew);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        userInfoPrefs = getApplication().getSharedPreferences("UserInfoPrefs", 0);

        String myEmail = userInfoPrefs.getString("email", null);
        if(myEmail != null){
            User.setMY_MAIN_URL(MAIN_URL_PREFIX + userInfoPrefs.getString("username", null) + MAIN_URL_SUFFIX);
            goToOnlineUserList = new Intent(ActivityLogin.this, ActivityOnlineUserList.class);
            goToOnlineUserList.putExtra("username", userInfoPrefs.getString("username", null));
            startActivity(goToOnlineUserList);
        }
    }

    public void onRegisterButtonClick(View view){
        Intent goToRegister = new Intent(ActivityLogin.this, ActivityRegister.class);
        startActivity(goToRegister);
    }

    public void onLoginButtonClick(View view){
        User.setMY_MAIN_URL(MAIN_URL_PREFIX + usrname_input.getText().toString() + MAIN_URL_SUFFIX);
        userInfoPrefs.edit().putString("email", et_email.getText().toString()).apply();
        userInfoPrefs.edit().putString("username", usrname_input.getText().toString()).apply();
        goToOnlineUserList = new Intent(ActivityLogin.this, ActivityOnlineUserList.class);
        goToOnlineUserList.putExtra("username", usrname_input.getText().toString());
        startActivity(goToOnlineUserList);
    }
}
