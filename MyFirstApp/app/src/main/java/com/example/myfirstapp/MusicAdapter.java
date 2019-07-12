package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {
    ArrayList<Music> music;
    public MusicAdapter(Context context, ArrayList<Music> music) {
        super(context, 0, music);
        this.music = music;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.music_item,parent, false);

        TextView label = convertView.findViewById(R.id.label);
        ImageView icon =  convertView.findViewById(R.id.image);

        label.setText(music.get(position).getLabel());
        icon.setImageResource(music.get(position).getIdImage());

        return convertView;
    }

}