package com.xtek.chatlite;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class LoginActivity extends Activity {

    private TextView usrname;
    private TextView passwd;
    private EditText usrname_input;
    private EditText passwd_input;
    private ImageView userIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIcon = (ImageView) findViewById(R.id.usricon);
        usrname = (TextView) findViewById(R.id.usrname);
        passwd = (TextView) findViewById(R.id.passwd);
        usrname_input = (EditText) findViewById(R.id.input_passwd);
        passwd_input = (EditText) findViewById(R.id.messageInput);

        userIcon.setImageResource(R.drawable.pewpewpew);


    }


}
