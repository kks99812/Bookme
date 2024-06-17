package com.cookandroid.bookapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        EditText bookTitleEditText = findViewById(R.id.bookTitleEditText);
        EditText authorEditText = findViewById(R.id.authorEditText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Switch favoriteSwitch = findViewById(R.id.favoriteSwitch);
        EditText reviewEditText = findViewById(R.id.reviewEditText);
        Button closeButton = findViewById(R.id.closeButton);
        Button saveButton = findViewById(R.id.saveButton);


        closeButton.setOnClickListener(v -> {
            bookTitleEditText.setText("");
            authorEditText.setText("");
            ratingBar.setRating(0);
            favoriteSwitch.setChecked(false);
            reviewEditText.setText("");

            finish();
        });

        saveButton.setOnClickListener(v -> {
            String bookTitle = bookTitleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();
            float rating = ratingBar.getRating();
            boolean isFavorite = favoriteSwitch.isChecked();
            String review = reviewEditText.getText().toString().trim();

            if (bookTitle.isEmpty() || author.isEmpty() || rating == 0.0 || review.isEmpty()) {
                Toast.makeText(ReviewActivity.this, "모두 작성해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("BookReviews", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Set<String> titles = sharedPreferences.getStringSet("titles", new HashSet<>());
                titles.add(bookTitle);
                editor.putStringSet("titles", titles);
                editor.putString(bookTitle + "_author", author); // 작가 정보 저장
                editor.putFloat(bookTitle + "_rating", rating);
                editor.putBoolean(bookTitle + "_favorite", isFavorite);
                editor.putString(bookTitle + "_review", review);

                editor.apply();
                Toast.makeText(ReviewActivity.this, "저장되었습니다!", Toast.LENGTH_SHORT).show();

                bookTitleEditText.setText("");
                authorEditText.setText("");
                ratingBar.setRating(0);
                favoriteSwitch.setChecked(false);
                reviewEditText.setText("");

                finish();
            }
        });
    }
}


