package io.middaymeal.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by siddharthgautam on 27/06/18.
 */

public class LoginActivity extends Activity implements VolleyApi.ResponseListener{
    Button login;
    EditText etUser,etPass;
    TextView forgetPass ,newUser;
    Toolbar toolbar;
    String type;
    RadioButton manager,school;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById();
    }

    private boolean isValidated(String strmob, String strpass) {

        if (strmob.length() == 0) {
            Toast.makeText(this, "please enter mobile no.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (strpass.length() == 0) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }


    private void findViewById() {
        etUser = (EditText)findViewById(R.id.login_etEmail);
        etPass = (EditText)findViewById(R.id.login_etPassword);
        login = (Button)findViewById(R.id.login_tvLogin);
        manager = (RadioButton)findViewById(R.id.manager);
        school = (RadioButton)findViewById(R.id.school);
        //forgetPass = (TextView)findViewById(R.id.login_tvForgotPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getuserDetail();
            }
        });
    }
    private void getuserDetail() {

        if (school.isChecked()){
            type="2";
        }else {
            type="1";
        }
        String str_user = etUser.getText().toString().trim();
        String str_pass = etPass.getText().toString().trim();
        if (isValidated(str_user,str_pass)){
            //startActivity(new Intent(this,MainActivity.class));
           VolleyApi.getInstance().login(this,this,str_user,str_pass,type);
        }
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);
                Utility.addPreferences(LoginActivity.this,Constants.keyLoginCheck,true);
                Utility.addPreferences(LoginActivity.this,Constants.id,json_data.getString("id"));
                Utility.addPreferences(LoginActivity.this,Constants.keyUserName,json_data.getString("user_name"));
                Utility.addPreferences(LoginActivity.this,Constants.password,json_data.getString("password"));
                if (type.equalsIgnoreCase("1")) {
                    Utility.addPreferences(LoginActivity.this, Constants.first_name, json_data.getString("first_name"));
                    Utility.addPreferences(LoginActivity.this, Constants.last_name, json_data.getString("last_name"));

                }
                Utility.addPreferences(LoginActivity.this, Constants.userType, type);

            }

            if (type.equalsIgnoreCase("2")){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }else {
                startActivity(new Intent(LoginActivity.this,FeedbackManagerMainActivity.class));
            }

            finish();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();

        }


    }

}
