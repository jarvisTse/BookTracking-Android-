package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    ApiService myAPIService;
    EditText et_username;
    EditText et_email;
    EditText et_password;
    EditText et_password_confirm;
    TextView tv_name_error;
    TextView tv_email_error;
    TextView tv_password_error;
    TextView tv_password_confirm_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button btn_signup = findViewById(R.id.btn_signup);
        ImageButton btn_back = findViewById(R.id.btn_back);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_password_confirm = findViewById(R.id.et_password_confirm);
        final TextView tv_username = findViewById(R.id.tv_username);
        final TextView tv_email = findViewById(R.id.tv_email);
        final TextView tv_password = findViewById(R.id.tv_password);
        final TextView tv_password_confirm = findViewById(R.id.tv_password_confirm);
        final TextView tv_hasAccount = findViewById(R.id.tv_hasAccount);
        tv_name_error = findViewById(R.id.tv_name_error);
        tv_email_error = findViewById(R.id.tv_email_error);
        tv_password_error = findViewById(R.id.tv_password_error);
        tv_password_confirm_error = findViewById(R.id.tv_password_confirm_error);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_name_error.setVisibility(View.GONE);
                tv_email_error.setVisibility(View.GONE);
                tv_password_error.setVisibility(View.GONE);
                tv_password_confirm_error.setVisibility(View.GONE);
                signup();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });

        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_username.setVisibility(View.VISIBLE);
                    et_username.setHint("");
                } else {
                    tv_username.setVisibility(View.GONE);
                    et_username.setHint("Username");
                }
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

        et_password_confirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_password_confirm.setVisibility(View.VISIBLE);
                    et_password_confirm.setHint("");
                } else {
                    tv_password_confirm.setVisibility(View.GONE);
                    et_password_confirm.setHint("Password Confirmation");
                }
            }
        });

        tv_hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }

    public void signup() {
        String name = et_username.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String password_confirm = et_password_confirm.getText().toString();
        myAPIService = RetrofitClient.getInstance().getAPI();
        Call<AuthMessage> call = myAPIService.signup(name, email, password, password_confirm);
        call.enqueue(new Callback<AuthMessage>() {
            @Override
            public void onResponse(Call<AuthMessage> call, Response<AuthMessage> response) {
                Log.d("Signup", response.toString());
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        SaveSharedPreference.setUserId(getApplicationContext(), String.valueOf(response.body().getUser_id()));
                        startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        if (response.body().getName() != null) {
                            tv_name_error.setText(response.body().getName());
                            tv_name_error.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getEmail() != null) {
                            tv_email_error.setText(response.body().getEmail());
                            tv_email_error.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getPassword() != null) {
                            tv_password_error.setText(response.body().getPassword());
                            tv_password_error.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getPassword_confirm() != null) {
                            tv_password_confirm_error.setText(response.body().getPassword_confirm());
                            tv_password_confirm_error.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthMessage> call, Throwable t) {
                Log.d("Signup", t.toString());
            }
        });
    }
}
