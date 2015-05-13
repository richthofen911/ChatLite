package com.xtek.chatlite;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityRegister extends Activity {

    private static String url_register_prefix = "http://104.167.102.201:8888/?email=";
    private String email = "";

    ImageView holderIcon;
    EditText et_email_reg;
    EditText et_username_reg;
    Button btn_register_reg;
    TextView tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        holderIcon = (ImageView) findViewById(R.id.usricon_reg);
        et_email_reg = (EditText) findViewById(R.id.input_email_reg);
        et_username_reg = (EditText) findViewById(R.id.input_name_reg);
        btn_register_reg = (Button) findViewById(R.id.btn_register_reg);
        tv_progress = (TextView) findViewById(R.id.tv_register_progress);

    }

    public void onRegisterButtonClickReg(View view){
        CreateAccount createAccount = new CreateAccount();
        String url_register = url_register_prefix + et_email_reg.getText().toString();
        Log.e("ready to exec ", "");
        createAccount.execute(url_register);
    }

    public class CreateAccount extends AsyncTask<String, Integer, String> {
        HTTPClient httpClient = new HTTPClient();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        @Override
        protected String doInBackground(String... params) {
            publishProgress(50);
            return httpClient.getRequest(params[0]);
        }

        @Override
        protected void onPostExecute(String result){
            if(result != null){
                Log.e("new app result: ", result);
            }
            progressBar.setVisibility(View.INVISIBLE);
            tv_progress.setVisibility(View.INVISIBLE);
            et_email_reg.setText("");
            et_username_reg.setText("");

            Intent goToLogin = new Intent(ActivityRegister.this, ActivityLogin.class);
            startActivity(goToLogin);
        }

        @Override
        protected void onProgressUpdate(Integer...values){
            progressBar.setProgress(values[0]);
            tv_progress.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            tv_progress.setText("Please Wait for A While");
        }

    }
}
