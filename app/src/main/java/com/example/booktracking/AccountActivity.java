package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    TextView tv_email;
    TextView tv_name;
    TextView tv_role;
    LinearLayout layout_logout;
    ApiService myAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_role = (TextView) findViewById(R.id.tv_role);
        layout_logout = (LinearLayout) findViewById(R.id.layout_logout);
        loadData();

        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        LinearLayout layout_search = (LinearLayout) findViewById(R.id.layout_search);
        layout_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, SearchActivity.class));
            }
        });

        LinearLayout layout_home = (LinearLayout) findViewById(R.id.layout_home);
        layout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, HomeActivity.class));
            }
        });

        LinearLayout layout_record = (LinearLayout) findViewById(R.id.layout_record);
        layout_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, BorrowActivity.class));
            }
        });

    }

    public void loadData() {
        myAPIService = RetrofitClient.getInstance().getAPI();
        int user_id = Integer.parseInt(SaveSharedPreference.getUserId(getApplicationContext()));
        Log.d("Account", String.valueOf(user_id));
        Call<User> call = myAPIService.getUser(user_id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("Account", response.toString());
                if (response.body() != null) {
                    tv_name.setText(response.body().getName());
                    tv_email.setText(response.body().getEmail());
                    tv_role.setText(response.body().getStringRole());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Myapp", t.toString());
            }
        });
    }

    public void logout() {
        SaveSharedPreference.setUserId(getApplicationContext(), "");
        startActivity(new Intent(AccountActivity.this, MainActivity.class));
        finish();
    }
}