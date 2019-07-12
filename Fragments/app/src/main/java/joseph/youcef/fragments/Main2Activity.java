package joseph.youcef.fragments;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // gestionnaire
        FragmentManager manager = getSupportFragmentManager();
        // transaction
        FragmentTransaction trans = manager.beginTransaction();

        // détecter l’orientation
        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == Configuration.ORIENTATION_PORTRAIT) {
            trans.replace(R.id.fraglay1, new MyFragmentDyn1());
        } else {
            trans.replace(R.id.fraglay1, new MyFragmentDyn2());
        }

         // mettre les fragments dans les réceptacles
//        trans.add(R.id.fraglay1, new MyFragmentDyn1());
//        trans.add(R.id.fraglay2, new MyFragmentDyn2());


        // faire un commit pour terminer
        trans.commit();
    }
}
