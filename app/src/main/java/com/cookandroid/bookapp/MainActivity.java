package com.cookandroid.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonMyBooks = findViewById(R.id.buttonMyBooks);
        Button buttonMyBest = findViewById(R.id.buttonMyBest);
        Button buttonWriteReadingLog = findViewById(R.id.buttonWriteReadingLog);

        buttonMyBooks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyBooksActivity.class);
            startActivity(intent);
        });

        buttonMyBest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyBestBooksActivity.class);
            startActivity(intent);
        });

        buttonWriteReadingLog.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            startActivity(intent);
        });

    }
}


