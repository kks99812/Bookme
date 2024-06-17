package com.cookandroid.bookapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView authorTextView = findViewById(R.id.authorTextView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Switch favoriteSwitch = findViewById(R.id.favoriteSwitch);
        TextView reviewTextView = findViewById(R.id.reviewTextView);
        Button closeButton = findViewById(R.id.closeButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        String bookTitle = getIntent().getStringExtra("bookTitle");

        SharedPreferences sharedPreferences = getSharedPreferences("BookReviews", Context.MODE_PRIVATE);
        String author = sharedPreferences.getString(bookTitle + "_author", "");
        float rating = sharedPreferences.getFloat(bookTitle + "_rating", 0);
        boolean isFavorite = sharedPreferences.getBoolean(bookTitle + "_favorite", false);
        String review = sharedPreferences.getString(bookTitle + "_review", "");

        titleTextView.setText(bookTitle);
        authorTextView.setText(author);
        ratingBar.setRating(rating);
        favoriteSwitch.setChecked(isFavorite);
        reviewTextView.setText(review);

        closeButton.setOnClickListener(v -> finish());

        deleteButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (bookTitle != null && !bookTitle.isEmpty()) {
                Set<String> titles = sharedPreferences.getStringSet("titles", new HashSet<>());
                if (titles != null && titles.contains(bookTitle)) {
                    titles.remove(bookTitle);
                    editor.putStringSet("titles", titles);
                    editor.remove(bookTitle + "_author");
                    editor.remove(bookTitle + "_rating");
                    editor.remove(bookTitle + "_favorite");
                    editor.remove(bookTitle + "_review");

                    editor.apply();
                    Toast.makeText(BookDetailActivity.this, "삭제되었습니다!", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(BookDetailActivity.this, "존재하지 않는 책입니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(BookDetailActivity.this, "책 제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
