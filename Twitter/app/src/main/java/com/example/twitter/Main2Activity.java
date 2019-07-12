package com.example.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        TextView textName = findViewById(R.id.textname);
        textName.setText(user.getName());

        TextView textTweet = findViewById(R.id.texttweet);
        textTweet.setText(user.getTweet());
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(user.getPhoto());



    }
}
