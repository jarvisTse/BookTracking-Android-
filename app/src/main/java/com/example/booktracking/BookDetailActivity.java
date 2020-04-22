package com.example.booktracking;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    ApiService myApiService;
    int book_id;
    Book book;
    TextView tv_title;
    TextView tv_author;
    TextView tv_type;
    TextView tv_publisher;
    TextView tv_publicationYear;
    TextView tv_language;
    TextView tv_isbn;
    TextView tv_pageNumber;
    TextView tv_status;
    TextView tv_description;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book_id = getIntent().getIntExtra("book_id", 0);
        setContentView(R.layout.activity_detail);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_publisher = (TextView) findViewById(R.id.tv_publisher);
        tv_publicationYear = (TextView) findViewById(R.id.tv_publicationYear);
        tv_language = (TextView) findViewById(R.id.tv_language);
        tv_isbn = (TextView) findViewById(R.id.tv_isbn);
        tv_pageNumber = (TextView) findViewById(R.id.tv_pageNumber);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_description = (TextView) findViewById(R.id.tv_description);
        iv_image = (ImageView) findViewById(R.id.iv_image);

        myApiService = RetrofitClient.getInstance().getAPI();
        Call<Book> call = myApiService.getBookbyId(book_id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d("Book Detail", response.toString());
                if (response.body() != null) {
                    book = response.body();
                    loadData();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Book Detail Error", t.toString());
            }
        });

        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadData() {
        tv_title.setText(book.getTitle());
        tv_author.setText(book.getAuthor());
        tv_type.setText(book.getType());
        tv_publisher.setText(book.getPublisher());
        tv_publicationYear.setText(book.getStringYear());
        tv_language.setText(book.getLanguage());
        tv_isbn.setText(book.getISBN());
        tv_pageNumber.setText(book.getStringPage());
        tv_status.setText(book.getStatus());
        tv_description.setText(book.getDescription());
        Picasso.get().load(Config.getImageUrl(book.getImage())).into(iv_image);
    }
}
