package joseph.youcef.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyView; // view
    private ArrayList<User> users; //model
    private UserAdapter userAdapter; //controller

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyView = findViewById(R.id.recycle);

        users = new ArrayList<>();
        // static data
        users.add(new User("MECHEREF AFAF","Hello from insta",R.drawable.user));
        users.add(new User("MECHEREF AFAF","Hello from insta",R.drawable.user));
        users.add(new User("MECHEREF AFAF","Hello from insta",R.drawable.user));
        users.add(new User("MECHEREF AFAF","Hello from insta",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("HAMIDI Oussama","Hi mate we are here ! ",R.drawable.user));
        users.add(new User("SAAD Hamid","Let's get started",R.drawable.user));

        recyView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(users);
        recyView.setAdapter(userAdapter);

    }
}
