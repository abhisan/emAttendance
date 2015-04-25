package com.em.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.em.AppController;
import com.em.R;
import com.em.enums.ResponseCode;
import com.em.helper.CallBack;
import com.em.helper.ResponseEntity;
import com.em.services.StudentService;
import com.em.services.impl.StudentServiceImpl;
import com.em.utils.EmUtils;
import com.em.vo.SClass;
import com.em.vo.User;

import java.util.List;

public class LoginActivity extends ActionBarActivity {
    private EditText userText;
    private EditText passText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkInternetConnection();
        userText = (EditText) findViewById(R.id.userid);
        passText = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.sign_in_button);
        progressDialog = new ProgressDialog(this);
        final Activity _this = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = userText.getText().toString();
                String password = passText.getText().toString();
                if (TextUtils.isEmpty(userId)) {
                    userText.setError(getString(R.string.error_field_required));
                } else if (TextUtils.isEmpty(password)) {
                    passText.setError(getString(R.string.error_field_required));
                } else {
                    User user = new User();
                    user.setUserId(userId);
                    user.setPassword(password);
                    StudentService studentService = new StudentServiceImpl();
                    EmUtils.showProgressDialog(progressDialog);
                    studentService.getServiceTicket(user, new CallBack<ResponseEntity<String>>() {
                        @Override
                        public void callBack(ResponseEntity<String> responseEntity) {
                            if (responseEntity.getResponseCode().equals(ResponseCode.OK.getResCode())) {
                                AppController.getInstance().setToken(responseEntity.getData().toString());
                                Intent i = new Intent(_this, SelectActivity.class);
                                i.putExtra("data", "value");
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid login credential.", Toast.LENGTH_SHORT).show();
                            }
                            EmUtils.hideProgressDialog(progressDialog);
                        }
                    }, new CallBack<VolleyError>() {
                        @Override
                        public void callBack(VolleyError volleyError) {
                            EmUtils.hideProgressDialog(progressDialog);
                            Toast.makeText(getApplicationContext(), "Could not connect to server.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, PreferencesActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            Toast.makeText(this, "No internet connection.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }
}
