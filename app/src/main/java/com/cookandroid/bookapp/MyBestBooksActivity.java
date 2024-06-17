package com.cookandroid.bookapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyBestBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_best_books);

        ListView bestBooksListView = findViewById(R.id.bestBooksListView);

        SharedPreferences sharedPreferences = getSharedPreferences("BookReviews", Context.MODE_PRIVATE);
        Set<String> bookTitles = sharedPreferences.getStringSet("titles", new HashSet<>());

        ArrayList<String> favoriteBooks = new ArrayList<>();
        for (String title : bookTitles) {
            boolean isFavorite = sharedPreferences.getBoolean(title + "_favorite", false);
            if (isFavorite) {
                favoriteBooks.add(title);
            }
        }

        BookListArray adapter = new BookListArray(this, favoriteBooks);
        bestBooksListView.setAdapter(adapter);

        bestBooksListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTitle = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(MyBestBooksActivity.this, BookDetailActivity.class);
            intent.putExtra("bookTitle", selectedTitle);
            startActivity(intent);
        });
    }
}

