package com.example.twitter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyFragment2 extends Fragment {

    MainActivity activ;

    User user;

    TextView textName;
    TextView textTweet;
    ImageView imageView;

    private LinearLayout linearLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_fragment2,container,false);
        textName = view.findViewById(R.id.textname);
        textTweet = view.findViewById(R.id.texttweet);
        imageView = view.findViewById(R.id.imageView2);

        linearLayout = view.findViewById(R.id.mylayout2);
        linearLayout.setVisibility(View.INVISIBLE);

//        textName.setVisibility(View.INVISIBLE);
//        textTweet.setVisibility(View.INVISIBLE);
//        imageView.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activ = (MainActivity) context;
    }

    public void setUser(User user){
        textName.setText(user.getName());
        textTweet.setText(user.getTweet());
        imageView.setImageResource(user.getPhoto());
        linearLayout.setVisibility(View.VISIBLE);

    }
}
