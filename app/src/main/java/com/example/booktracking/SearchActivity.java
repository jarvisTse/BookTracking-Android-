package com.example.booktracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ApiService myAPIService;
    Spinner spinner_filter;
    EditText et_search;
    LinearLayout layout_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final String[] spin_filter = {"Title", "Author", "Type", "Publisher", "Language", "ISBN", "description" };
        spinner_filter = (Spinner) findViewById(R.id.spinner_filter);
        ArrayAdapter<String> adapter_filter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spin_filter);
        adapter_filter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_filter.setAdapter(adapter_filter);

        load_data(null, null);

        layout_filter = (LinearLayout) findViewById(R.id.layout_filter);

        Button btn_filter = (Button) findViewById(R.id.btn_filter);
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_filter.getVisibility() == View.VISIBLE) {
                    layout_filter.setVisibility(View.GONE);
                    spinner_filter.setSelection(0);
                } else {
                    layout_filter.setVisibility(View.VISIBLE);
                }
            }
        });

        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            search();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        Button btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        LinearLayout layout_home = (LinearLayout) findViewById(R.id.layout_home);
        layout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, HomeActivity.class));
            }
        });

        LinearLayout layout_record = (LinearLayout) findViewById(R.id.layout_record);
        layout_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, BorrowActivity.class));
            }
        });

        LinearLayout layout_account = (LinearLayout) findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, AccountActivity.class));
            }
        });
    }

    public void load_data(String content, String field) {
        myAPIService = RetrofitClient.getInstance().getAPI();
        Call<List<Book>> call = myAPIService.getBooks(content, field);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                Log.d("Search", "message:   " + response.toString());
                final List<Book> books = response.body();
                BookAdapter masksAdapter = new BookAdapter(SearchActivity.this, R.layout.book_item, books);
                ListView list_search = (ListView) findViewById(R.id.list_search);
                list_search.setAdapter(masksAdapter);

                list_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
                        intent.putExtra("book_id", books.get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }

    public void search() {
        String content = et_search.getText().toString();
        String field = spinner_filter.getSelectedItem().toString();
        load_data(content, field);
        layout_filter.setVisibility(View.GONE);
        spinner_filter.setSelection(0);
    }
}
