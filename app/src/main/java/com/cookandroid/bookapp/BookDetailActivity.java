package com.cookandroid.bookapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

        String bookTitle = getIntent().getStringExtra("bookTitle");

        SharedPreferences sharedPreferences = getSharedPreferences("BookReviews", Context.MODE_PRIVATE);
        float rating = sharedPreferences.getFloat(bookTitle + "_rating", 0);
        boolean isFavorite = sharedPreferences.getBoolean(bookTitle + "_favorite", false);
        String review = sharedPreferences.getString(bookTitle + "_review", "");

        titleTextView.setText(bookTitle);
        ratingBar.setRating(rating);
        favoriteSwitch.setChecked(isFavorite);
        reviewTextView.setText(review);

        closeButton.setOnClickListener(v -> finish());
    }
}
