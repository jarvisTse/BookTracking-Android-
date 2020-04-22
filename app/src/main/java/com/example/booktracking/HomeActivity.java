package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(SaveSharedPreference.getUserId(HomeActivity.this).length() == 0){
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }

        LinearLayout layout_search = (LinearLayout) findViewById(R.id.layout_search);
        layout_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });

        LinearLayout layout_record = (LinearLayout) findViewById(R.id.layout_record);
        layout_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BorrowActivity.class));
            }
        });

        LinearLayout layout_account = (LinearLayout) findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
            }
        });
    }
}
