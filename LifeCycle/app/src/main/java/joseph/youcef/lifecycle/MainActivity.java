package joseph.youcef.lifecycle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String HELLO_STATE="hello";
    TextView textView ;
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        edit1= findViewById(R.id.edit1);
        final Button bt1=findViewById(R.id.bt1);
        final ImageView im2= findViewById(R.id.im2);
        final Button bt2 = findViewById(R.id.bt2);
        textView = findViewById(R.id.textView);



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText( "Hi " + edit1.getText().toString()+" :)");
                bt2.setVisibility(View.INVISIBLE);
            }
        });



        Animation animation = AnimationUtils.loadAnimation(this, R.anim.monanim);
        edit1.setAnimation(animation);
        bt1.setAnimation(animation);
        im2.startAnimation(animation);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(savedInstanceState != null){
            textView.setText(savedInstanceState.getString(HELLO_STATE,"not working"));
            bt2.setVisibility(View.INVISIBLE);


        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(HELLO_STATE,"Hi " + edit1.getText().toString()+" :)");
        Toast.makeText(this, "onSave", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {
            Toast.makeText(this, "onPause, l'utilisateur à demandé la fermeture via un finish()", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "onPause, l'utilisateur n'a pas demandé la fermeture via un finish()", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

}
