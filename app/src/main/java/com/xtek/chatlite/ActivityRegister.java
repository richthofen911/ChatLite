package com.xtek.chatlite;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityRegister extends ActionBarActivity {

    private static String url_register_prefix = "http://192.168.128.98:8888/?email=";
    private String email = "";

    ImageView holderIcon;
    EditText et_email_reg;
    EditText et_username_reg;
    Button btn_register_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        holderIcon = (ImageView) findViewById(R.id.usricon_reg);
        et_email_reg = (EditText) findViewById(R.id.input_email_reg);
        et_username_reg = (EditText) findViewById(R.id.input_name_reg);
        btn_register_reg = (Button) findViewById(R.id.btn_register_reg);


    }

    public void onRegisterButtonClickReg(View view){
        CreateAccount createAccount = new CreateAccount();
        String url_register = url_register_prefix + et_email_reg.getText().toString();
        et_email_reg.setText("");
        createAccount.execute(url_register);
    }

    public class CreateAccount extends AsyncTask<String, String, String> {
        HTTPClient httpClient = new HTTPClient();
        @Override
        protected String doInBackground(String... params) {
            String receive = httpClient.getRequest(params[0]);
            Log.e("result: ", receive);
            return receive;
        }
        protected void onPostExecute(String result){
            if(result != null){
                //httpClient.parseJSON(result);

                //Log.e("beacon in httpclient ", httpClient.getBeacons().toString());
                //beaconsFromUrl.addAll(httpClient.getBeacons());
                //aBeacon = httpClient.getBeacons().get(0);
                //beaconId = aBeacon.getBeaconId();
                //url_company = url_company_prefix + aBeacon.getCompanyId() + "/" + aBeacon.getUuid() +"/"+ aBeacon.getMajor() + "/" + aBeacon.getMinor();
                //DataStore.setMessageUrl(url_company);
            }
        }


    }
}
