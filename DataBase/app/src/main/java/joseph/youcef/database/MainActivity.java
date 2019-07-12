package joseph.youcef.database;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public
class MainActivity extends AppCompatActivity {


    TextView score;
    int USER_LEVEL = 0;
    Button guess;
    Button again;
    TextView f1;
    TextView f2;
    EditText e1;
    EditText e2;
    MaBaseManager maBaseManager;
    String myword;

    // shared pref Initialization
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // shared pref
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        if(pref.contains("score")){
            USER_LEVEL = pref.getInt("score",0);
        }

        // loader
        //getLoaderManager().initLoader(1,null,this);

        score = findViewById(R.id.score);
        guess = findViewById(R.id.button);
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        again = findViewById(R.id.button1);


        // animation
        niceAnimations();



        // database initialisation
        init_db();

        if(USER_LEVEL <10){
            myword = currentWord(USER_LEVEL+1);
            f1.setText(""+myword.charAt(0));
            f2.setText(""+myword.charAt(3));
            e1.setHint("?");
            e2.setHint("?");
        } else if(USER_LEVEL == 10){  // for restarting the game
           savedLast();
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_rotate);
            score.startAnimation(animation);
        }


        // the guess button
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals(""+ myword.charAt(1)) && e2.getText().toString().equals(""+ myword.charAt(2))){
                    USER_LEVEL++;
                    score.setText(USER_LEVEL+"/10");
                    if(USER_LEVEL!=10){
                        myword = currentWord(USER_LEVEL+1);
                        f1.setText(""+myword.charAt(0));
                        f2.setText(""+myword.charAt(3));
                        switch (USER_LEVEL){
                            case 1:
                                Toast.makeText(getApplicationContext(),"Yes Noob level :) ",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(),"Not Bad ^^ ",Toast.LENGTH_SHORT).show();

                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(),"go forward *-* ",Toast.LENGTH_SHORT).show();

                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(),"amazing ;)",Toast.LENGTH_SHORT).show();

                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(),"amazing ;) V2.0",Toast.LENGTH_SHORT).show();

                                break;
                            case 6:
                                Toast.makeText(getApplicationContext(),"amazing ;) V3.0",Toast.LENGTH_SHORT).show();

                                break;
                            case 7:
                                Toast.makeText(getApplicationContext(),"Lol not V4.0 great job",Toast.LENGTH_SHORT).show();
                                break;
                            case 8:
                                Toast.makeText(getApplicationContext(),"Are You cracker ?",Toast.LENGTH_SHORT).show();

                                break;
                            case 9:
                                Toast.makeText(getApplicationContext(),"Yes sweety :) ",Toast.LENGTH_SHORT).show();
                                break;


                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Wow YOu ar3 @ h@ck3r 100%",Toast.LENGTH_LONG).show();
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_rotate);
                        score.startAnimation(animation);
                        savedLast();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Nah try again :( ",Toast.LENGTH_SHORT).show();
                }
            }
        });


        // the again button
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("score");
                USER_LEVEL = 0;
                score.setText(USER_LEVEL+"/10");
                editor.commit();
                guess.setVisibility(View.VISIBLE);
                f1.setVisibility(View.VISIBLE);
                f2.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                myword = currentWord(USER_LEVEL+1);
                f1.setText(""+myword.charAt(0));
                f2.setText(""+myword.charAt(3));
                again.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Here we Go Again Yey *__*",Toast.LENGTH_SHORT).show();
                score.animate().cancel();
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                score.startAnimation(animation);


            }
        });
    }


    // get the current word from db
    public String currentWord(int id){
        String w="";
        Cursor cursor = maBaseManager.getWordById(id);
        if (cursor.moveToFirst()) {
            w = cursor.getString(cursor.getColumnIndex("word"));
            // Toast.makeText(getApplicationContext(),myword,Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        return w;

    }


    public void init_db(){
        maBaseManager = new MaBaseManager(this);
        Cursor cursor = maBaseManager.getAllWords();
        int nb = cursor.getCount();
        if(nb==0){
            maBaseManager.addWord("Moon");
            maBaseManager.addWord("Test");
            maBaseManager.addWord("Huge");
            maBaseManager.addWord("Body");
            maBaseManager.addWord("Done");
            maBaseManager.addWord("Luck");
            maBaseManager.addWord("Stop");
            maBaseManager.addWord("Star");
            maBaseManager.addWord("Blue");
            maBaseManager.addWord("Node");
            score.setText(USER_LEVEL+"/10");
            //Toast.makeText(getApplicationContext(),"nb =0",Toast.LENGTH_SHORT).show();

        } else{
            score.setText(USER_LEVEL+"/"+nb);
            //Toast.makeText(getApplicationContext(),"nb not 0",Toast.LENGTH_SHORT).show();
        }

        cursor.close();



    }


    // for restaring
    public void savedLast(){
        guess.setVisibility(View.GONE);
        again.setVisibility(View.VISIBLE);
        f1.setVisibility(View.GONE);
        f2.setVisibility(View.GONE);
        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putInt("score",USER_LEVEL);
        editor.commit(); // commit changes

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        maBaseManager.close();


    }

    public void niceAnimations(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        score.startAnimation(animation);


        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trans1);
        f1.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trans2);
        e1.startAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trans1);
        e2.startAnimation(animation3);

        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trans2);
        f2.startAnimation(animation4);
    }

//
//    //loader
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new MonCursorLoader(getApplicationContext());
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        this.ChargeCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
}
