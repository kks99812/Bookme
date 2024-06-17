package com.cookandroid.bookapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class MyBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        ListView booksListView = findViewById(R.id.booksListView);

        SharedPreferences sharedPreferences = getSharedPreferences("BookReviews", Context.MODE_PRIVATE);
        Set<String> bookTitles = sharedPreferences.getStringSet("titles", null);

        ArrayList<String> titlesList = new ArrayList<>();
        if (bookTitles != null) {
            titlesList.addAll(bookTitles);
        }

        BookListArray adapter = new BookListArray(this, titlesList);
        booksListView.setAdapter(adapter);

        booksListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTitle = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(MyBooksActivity.this, BookDetailActivity.class);
            intent.putExtra("bookTitle", selectedTitle);
            startActivity(intent);
        });
    }
}

