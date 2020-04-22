package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ApiService myAPIService;
    TokenManager tokenManager;
    Call<AuthMessage> call;
    EditText et_email;
    EditText et_password;
    TextView tv_email_error;
    TextView tv_password_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAPIService = RetrofitClient.getInstance().getAPI();
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
//        if(tokenManager.getToken().getAccessToken() != null){
//            startActivity(new Intent(LoginActivity.this, SearchActivity.class));
//            finish();
//        }

        Button btn_login = findViewById(R.id.btn_login);
        ImageButton btn_back = findViewById(R.id.btn_back);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        final TextView tv_email = findViewById(R.id.tv_email);
        final TextView tv_password = findViewById(R.id.tv_password);
        final TextView tv_noAccount = findViewById(R.id.tv_noAccount);
        tv_email_error = findViewById(R.id.tv_email_error);
        tv_password_error = findViewById(R.id.tv_password_error);

        btn_login.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv_email_error.setVisibility(View.GONE);
                tv_password_error.setVisibility(View.GONE);
                login();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_email.setVisibility(View.VISIBLE);
                    et_email.setHint("");
                } else {
                    tv_email.setVisibility(View.GONE);
                    et_email.setHint("Email");
                }
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_password.setVisibility(View.VISIBLE);
                    et_password.setHint("");
                } else {
                    tv_password.setVisibility(View.GONE);
                    et_password.setHint("Password");
                }
            }
        });

        tv_noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    public void login() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        call = myAPIService.login(email, password);
        call.enqueue(new Callback<AuthMessage>() {
            @Override
            public void onResponse(Call<AuthMessage> call, Response<AuthMessage> response) {
                Log.d("Login", response.toString());
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        SaveSharedPreference.setUserId(getApplicationContext(), String.valueOf(response.body().getUser_id()));
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }
                    else {
                        if (response.body().getEmail() != null) {
                            tv_email_error.setText(response.body().getEmail());
                            tv_email_error.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getPassword() != null) {
                            tv_password_error.setText(response.body().getPassword());
                            tv_password_error.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthMessage> call, Throwable t) {
                Log.d("Login", t.toString());
            }
        });
    }
}
