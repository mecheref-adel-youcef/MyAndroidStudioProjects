package com.example.twitter;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {


    Dialog dialog;
    private static int random=0;

    FragmentManager manager;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        myFragment1 = (MyFragment1) manager.findFragmentById(R.id.fragment1);
        myFragment2 = (MyFragment2) manager.findFragmentById(R.id.fragment2);




        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_tweet);

    }


    // create a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }

    // handle the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_icon){

            dialog.show();
            dialog.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //random user
                    random++;


                    EditText editText = dialog.findViewById(R.id.editText);
                    String tweet = editText.getText().toString();
                    if(random % 2 == 0)
                        myFragment1.users.add(new User("SAAD Hamid",tweet,R.drawable.user));
                    else if (random % 3 ==0 )
                        myFragment1.users.add(new User("HAMIDI Oussama",tweet,R.drawable.user));
                    else
                        myFragment1.users.add(new User("MECHEREF Adel Youcef",tweet,R.drawable.user));

                    myFragment1.mAdapter.notifyDataSetChanged();


                    dialog.cancel();

                }
            });


            //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);

    }



    // pour chaque element
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user= (User) parent.getItemAtPosition(position);

        int displayMode = getResources().getConfiguration().orientation;

        if(displayMode == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent().setClass(this,Main2Activity.class);
            intent.putExtra("user", (Serializable) user);
            startActivity(intent);
        } else {
            myFragment2.setUser(user);
        }

    }



}







