package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clockwise(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        textView.startAnimation(animation);
    }

    public void zoom(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        textView.startAnimation(animation1);
    }

    public void fade(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        textView.startAnimation(animation1);
    }

    public void blink(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        textView.startAnimation(animation1);
    }

    public void move(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        textView.startAnimation(animation1);
    }

    public void slide(View view){
        TextView textView = findViewById(R.id.textview);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        textView.startAnimation(animation1);
    }
}
