package com.cookandroid.bookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookListArray extends ArrayAdapter<String> {
    private Context context;
    private List<String> titles;

    public BookListArray(Context context, List<String> titles) {
        super(context, 0, titles);
        this.context = context;
        this.titles = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(titles.get(position));

        if (position % 2 == 0) {
            convertView.setBackgroundResource(R.drawable.book1);
        } else {
            convertView.setBackgroundResource(R.drawable.book2);
        }

        return convertView;
    }
}
