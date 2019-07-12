package joseph.youcef.shopili;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreen extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        ImageView imageView = findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        imageView.startAnimation(animation);


        textView = findViewById(R.id.textView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        textView.startAnimation(animation1);





        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Shopili ...");

                startActivity(new Intent().setClass(getApplicationContext(),MainActivity.class));

                finish();
            }
        }, secondsDelayed * 1000);




    }


    @Override
    protected void onResume() {
        super.onResume();
        textView.setText("Shopili ..");

    }


}
