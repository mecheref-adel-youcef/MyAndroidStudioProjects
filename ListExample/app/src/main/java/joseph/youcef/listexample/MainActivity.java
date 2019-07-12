package joseph.youcef.listexample;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {


    private ListView listView; // view
    private UserAdapter mAdapter; // controller
    private ArrayList<User> users; //model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.mylist);

        users = new ArrayList<>();
        // static data
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("HAMIDI Oussama","Hi mate we are here ! ",R.drawable.user));
        users.add(new User("SAAD Hamid","Let's get started",R.drawable.user));

        mAdapter = new UserAdapter(this,users);
        listView.setAdapter(mAdapter);
//        setListAdapter(mAdapter);


        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user= (User) parent.getItemAtPosition(position);
        Toast.makeText(this,"Ohayo "+user.getName(),Toast.LENGTH_SHORT).show();
    }
}
