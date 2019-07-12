package joseph.youcef.shopili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);


        imageView1 = findViewById(R.id.img1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"12000 DA",Toast.LENGTH_SHORT).show();
            }
        });
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),ProductDetails.class);
                Product product= new Product(R.drawable.img1,12200,12000,"so super swatch realistic style 2019 EWZ2568","free delivery yes ! ","60 days until you decide ^_^","https://www.google.com/webhp?q=img1");
                intent.putExtra("product",product);
                startActivity(intent);
                return true;
            }
        });
        imageView1.startAnimation(animation);


        imageView2 = findViewById(R.id.img2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"20000 DA",Toast.LENGTH_SHORT).show();
            }
        });
        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),ProductDetails.class);
                Product product= new Product(R.drawable.img2,20200,20000,"gentelman swatch try it ;) YTR7844","20% from the price ","45 days until you decide :o","https://www.google.com/webhp?q=img2");
                intent.putExtra("product",product);
                startActivity(intent);
                return true;
            }
        });
        imageView2.startAnimation(animation);


        imageView3 = findViewById(R.id.img3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"15000 DA",Toast.LENGTH_SHORT).show();
            }
        });
        imageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),ProductDetails.class);
                Product product= new Product(R.drawable.img3,15200,15000,"so super swatch realistic style 2019 EWZ2568","20% from the price  ","3 days !!!","https://www.google.com/webhp?q=img3");
                intent.putExtra("product",product);
                startActivity(intent);
                return true;
            }
        });
        imageView3.startAnimation(animation);



        imageView4 = findViewById(R.id.img4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"14000 DA",Toast.LENGTH_SHORT).show();
            }
        });
        imageView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),ProductDetails.class);
                Product product= new Product(R.drawable.img4,14200,14000,"so super power swatch realistic style 2019 EWZ2568","free delivery yes ! ","30 days enjoy :p","https://www.google.com/webhp?q=img4");
                intent.putExtra("product",product);
                startActivity(intent);
                return true;
            }
        });
        imageView4.startAnimation(animation);



        imageView5 = findViewById(R.id.img5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"13000 DA",Toast.LENGTH_SHORT).show();
            }
        });
        imageView5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),ProductDetails.class);
                Product product= new Product(R.drawable.img5,13000,13000,"swatch water resist sweet style  UUTJ45","free delivery yes *.* ","60 days until you decide ^_^","https://www.google.com/webhp?q=img5");
                intent.putExtra("product",product);
                startActivity(intent);
                return true;
            }
        });
        imageView5.startAnimation(animation);


    }





}
