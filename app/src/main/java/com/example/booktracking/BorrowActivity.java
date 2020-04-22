package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowActivity extends AppCompatActivity {

    ApiService myAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_borrow);

        loadRecord();


        LinearLayout layout_search = (LinearLayout) findViewById(R.id.layout_search);
        layout_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BorrowActivity.this, SearchActivity.class));
            }
        });

        LinearLayout layout_home = (LinearLayout) findViewById(R.id.layout_home);
        layout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BorrowActivity.this, HomeActivity.class));
            }
        });

        LinearLayout layout_account = (LinearLayout) findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BorrowActivity.this, AccountActivity.class));
            }
        });
    }

    public void loadRecord() {
        int user_id = Integer.parseInt(SaveSharedPreference.getUserId(getApplicationContext()));
        myAPIService = RetrofitClient.getInstance().getAPI();
        Call<List<Record>> call = myAPIService.getRecords(user_id);
        call.enqueue(new Callback<List<Record>>() {
            @Override
            public void onResponse(Call<List<Record>> call, Response<List<Record>> response) {
                Log.d("Record", response.toString());
                if (response.body() != null) {
                    final List<Record> records = response.body();
                    TextView tv_record_count = (TextView) findViewById(R.id.tv_record_count);
                    String record_count = "Record found: " + records.size();
                    tv_record_count.setText(record_count);
                    RecordAdapter recordAdapter = new RecordAdapter(BorrowActivity.this, R.layout.record_item, records);
                    TextView tv_record_top = (TextView) findViewById(R.id.tv_record_top);
                    if (response.code() == 201) {
                        tv_record_top.setText(R.string.handle_record);
                    } else {
                        tv_record_top.setText(R.string.borrow_record);
                    }
                    ListView list_record = (ListView) findViewById(R.id.list_record);
                    list_record.setAdapter(recordAdapter);
                    list_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(BorrowActivity.this, BookDetailActivity.class);
                            intent.putExtra("book_id", records.get(position).getBook_id());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Record>> call, Throwable t) {

            }
        });
    }
}