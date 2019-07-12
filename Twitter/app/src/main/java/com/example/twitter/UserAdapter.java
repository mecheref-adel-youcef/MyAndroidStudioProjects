package com.example.twitter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private List<User> userList ;


    public UserAdapter(@NonNull Context context, ArrayList<User> list) {
        super(context, 0 , list);
        this.mContext = context;
        this.userList = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);


        // optimisation affichage des vues mini controller
        UserViewHolder userViewHolder = (UserViewHolder) listItem.getTag();
        if(userViewHolder == null){
            userViewHolder = new UserViewHolder();
            userViewHolder.name = listItem.findViewById(R.id.textView2);
            userViewHolder.description = listItem.findViewById(R.id.textView3);
            userViewHolder.photo = listItem.findViewById(R.id.imageView);
            listItem.setTag(userViewHolder);
        }

        User currentUser = userList.get(position);

        ImageView imageView  = listItem.findViewById(R.id.imageView);
        imageView.setImageResource(currentUser.getPhoto());

        TextView name = listItem.findViewById(R.id.textView2);
        name.setText(currentUser.getName());

        TextView release = listItem.findViewById(R.id.textView3);
        release.setText(currentUser.getTweet());

        return listItem;


    }
}