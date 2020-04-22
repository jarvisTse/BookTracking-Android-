package com.example.booktracking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        @SuppressLint("ViewHolder") View oneMaskView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent, false);

        TextView tv_title = (TextView) oneMaskView.findViewById(R.id.tv_title);
        TextView tv_author = (TextView) oneMaskView.findViewById(R.id.tv_author);
        TextView tv_type = (TextView) oneMaskView.findViewById(R.id.tv_type);
        TextView tv_status = (TextView) oneMaskView.findViewById(R.id.tv_status);
        TextView tv_description = (TextView) oneMaskView.findViewById(R.id.tv_description);
        ImageView iv_image = (ImageView) oneMaskView.findViewById(R.id.iv_image);

        String author = "Author: " + book.getAuthor();
        String type = "Type: " + book.getType();
        String status = "Status: " + book.getStatus();
        String description = "Description: " + book.getDescription();
        tv_title.setText(book.getTitle());
        tv_author.setText(author);
        tv_type.setText(type);
        tv_status.setText(status);
        tv_description.setText(description);

        Picasso.get().load(Config.getImageUrl(book.getImage())).into(iv_image);

        return oneMaskView;
    }
}
