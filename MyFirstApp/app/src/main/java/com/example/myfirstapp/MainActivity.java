package com.example.myfirstapp;
import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private ListView listView;
    private Button buttonDownload;
    private EditText editText;

    private MusicAdapter adapter;
    private ArrayList<Music> music;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private String fileName;
    private long downloadID;
    private int music_index = -1;
    private int current_music_index = -1;
    private File root_dir = Environment.getExternalStorageDirectory();   // path to root directory



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        music = new ArrayList<>();
        initiateMusicList();
        adapter = new MusicAdapter(MainActivity.this, music);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                music_index = position;
            }
        });

        editText = findViewById(R.id.editText);
        editText.setText("http://www.freemusicdownloads.cc/download/alan-walker-faded-medium-quality?id=http%3A%2F%2Fapi.soundcloud.com%2Ftracks%2F271004119%2Fstream%3Fclient_id%3Da3e059563d7fd3372b49b37f00a00bcf%26.mp3%26title%3Dalan-walker-faded-medium-quality");
        imageButton = findViewById(R.id.play_pause);
        buttonDownload =findViewById(R.id.download);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().equals("")){
//                    String uri = "http://srv02.yt-download.org/@download/251-5d0beb0e8ae66-3664000-229-128-webm-3480913/mp3/Az-mGR-CehY/K-391%2B%2526amp%253B%2BAlan%2BWalker%2B-%2BIgnite%2B%2528feat.%2BJulie%2BBergan%2B%2526amp%253B%2BSeungri%2529.mp3";
                    if (URLUtil.isValidUrl(editText.getText().toString()))
                        new DownloadFilesTask(MainActivity.this).execute(editText.getText().toString());
                    else
                        Toast.makeText(MainActivity.this, "URI not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initiateMusicList() {
        String path = root_dir.toString() + "/musicTP7";
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            music.add(new Music(files[i].getName(), R.drawable.music));
        }
    }

    //______________________________________________________________________________________________

    public void play_pause(View view) {
        if (music_index == -1){
            Toast.makeText(getApplicationContext(), "No music selected yet", Toast.LENGTH_SHORT).show();
        } else if (music_index != current_music_index){
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            Uri myUri = Uri.parse(String.valueOf(root_dir.toURI() + "musicTP7/" + music.get(music_index).getLabel()));
            mediaPlayer = MediaPlayer.create(this, myUri);
            mediaPlayer.start();
            imageButton.setBackgroundResource(R.drawable.pause);
            current_music_index = music_index;
        } else {
            Toast.makeText(getApplicationContext(), "released", Toast.LENGTH_SHORT).show();
            mediaPlayer.release();
            current_music_index = -1;
            imageButton.setBackgroundResource(R.drawable.play);
        }
    }

    public void prevMusic(View view) {
        int music_nb = music.size();
        if (!music.isEmpty()) {
            if (music_index > 0)
                music_index --;
            else
                music_index = music_nb-1;
        }
        Toast.makeText(getApplicationContext(), music_index + "--", Toast.LENGTH_SHORT).show();
        play_pause(view);
    }

    public void nextMusic(View view) {
        int music_nb = music.size();
        if (!music.isEmpty()) {
            if (music_index < music_nb - 1)
                music_index ++;
            else
                music_index = 0;
        }
        Toast.makeText(getApplicationContext(), music_index + "++", Toast.LENGTH_SHORT).show();
        play_pause(view);
    }
    //______________________________________________________________________________________________

    private String beginDownload(String myURI){
        fileName = getFileName(myURI);
        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(myURI))
                .setTitle(fileName)// Title of the Download Notification
                .setDescription("Downloading")// Description of the Download Notification
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationUri(Uri.parse(String.valueOf(root_dir.toURI() + "/musicTP7/" + fileName)))// Uri of the destination file
                .setRequiresCharging(false)// Set if charging is required to begin the download
                .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                .setAllowedOverRoaming(true);// Set if download is allowed on roaming network

        if (fileName.substring(fileName.length()-3).equals("mp3")) {
            if (!checkIfDownloaded(fileName)){
                if (haveStoragePermission()){
                    downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.
                    return "Downloading ...";
                }
            } else
                return "Music already  downloaded";
        } else
            return "this is not a music (mp3 ...)";
        return "";
    }

    private String getFileName(String myURI) {
        String s = "";
        String[] words = myURI.split("/");
        if (words[words.length-1].contains(".mp3")) {
            int index = words[words.length-1].indexOf(".mp3");
            s = words[words.length-1].substring(0, index+4);
            s = s.substring(0, 20) + ".mp3";
            // decode fileName special character extracted from uri
            try {
                s = URLDecoder.decode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else
            return  "empty ...";
        return s;
    }

    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {
                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }

    private boolean checkIfDownloaded(String fileName) {
        for (Music m : music)
            if (m.getLabel().equals(fileName))
                return true;
        return false;
    }
    //______________________________________________________________________________________________

    public class DownloadFilesTask extends AsyncTask<String, Void, String> {

        public DownloadFilesTask(Context context) { }

        @Override
        protected String doInBackground(String... strings) {
            String stat = beginDownload(strings[0]);
            return stat;
        }

        @Override
        protected void onPostExecute(String stat) {
            Toast.makeText(getApplicationContext(), stat, Toast.LENGTH_SHORT).show();
        }
    }
    //______________________________________________________________________________________________

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadID == id) {
                Toast.makeText(MainActivity.this, "Download Completed",
                        Toast.LENGTH_SHORT).show();
                music.add(new Music(fileName, R.drawable.music));
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }
}