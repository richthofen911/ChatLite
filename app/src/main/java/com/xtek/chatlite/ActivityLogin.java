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
    private EditText et_name;
    private EditText passwd_input;
    private ImageView userIcon;
    private String usrname;
    private String passwd;

    private Intent goToOnlineUserList;
    private SharedPreferences userInfoPrefs;

    private static final String MAIN_URL_PREFIX = "https://clqq";
    private static final String MAIN_URL_SUFFIX = ".firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIcon = (ImageView) findViewById(R.id.usricon);
        et_email = (EditText) findViewById(R.id.et_input_email);
        et_name = (EditText) findViewById(R.id.et_input_name);
        userIcon.setImageResource(R.drawable.pewpewpew);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        userInfoPrefs = getApplication().getSharedPreferences("UserInfoPrefs", 0);

        String myEmail = userInfoPrefs.getString("email", null);
        if(myEmail != null){
            Me.setMY_MAIN_URL(MAIN_URL_PREFIX + myEmail.substring(2, 7) + MAIN_URL_SUFFIX);
            Me.setUserName(userInfoPrefs.getString("username", null));
            goToOnlineUserList = new Intent(ActivityLogin.this, ActivityOnlineUserList.class);
            startActivity(goToOnlineUserList);
        }
    }

    public void onRegisterButtonClick(View view){
        Intent goToRegister = new Intent(ActivityLogin.this, ActivityRegister.class);
        startActivity(goToRegister);
    }

    public void onLoginButtonClick(View view){
        Me.setMY_MAIN_URL(MAIN_URL_PREFIX + et_email.getText().toString().substring(2, 7) + MAIN_URL_SUFFIX);
        Me.setUserName(et_name.getText().toString());
        Me.setUserEmail(et_email.getText().toString());
        userInfoPrefs.edit().putString("email", et_email.getText().toString()).apply();
        userInfoPrefs.edit().putString("username", et_name.getText().toString()).apply();
        goToOnlineUserList = new Intent(ActivityLogin.this, ActivityOnlineUserList.class);
        startActivity(goToOnlineUserList);
    }
}
