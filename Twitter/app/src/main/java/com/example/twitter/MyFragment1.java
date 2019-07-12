package com.example.twitter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MyFragment1 extends Fragment {

    private MainActivity activ;

     ListView listView;
     UserAdapter mAdapter;
     ArrayList<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_fragment1,container,false);
        listView = view.findViewById(R.id.listView1);

        users = new ArrayList<>();
        // static data
        users.add(new User("MECHEREF Adel Youcef","Hello from tweeter",R.drawable.user));
        users.add(new User("HAMIDI Oussama","Hi mate we are here ! ",R.drawable.user));
        users.add(new User("SAAD Hamid","Let's get started",R.drawable.user));

        mAdapter = new UserAdapter(activ,users);
        listView.setAdapter(mAdapter);




        listView.setOnItemClickListener(activ);
        if(savedInstanceState != null){
            users = (ArrayList<User>) savedInstanceState.getSerializable("users");
            mAdapter = new UserAdapter(activ,users);
            listView.setAdapter(mAdapter);
        }
        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activ = (MainActivity) context;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("users",users);
    }
}
