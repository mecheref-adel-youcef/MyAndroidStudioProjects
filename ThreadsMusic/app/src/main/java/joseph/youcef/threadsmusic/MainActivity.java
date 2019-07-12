package joseph.youcef.threadsmusic;


import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        //https://ss1.onlinevideoconverter.com/download?file=c2c2c2d3h7f5d3b1
        // minimised https://urlz.fr/a27g

    Button play,pause,stop,download;
    EditText musicUrl;
    MediaPlayer mediaPlayer;
    int pauseCurrentPosition;
    private long downloadReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        play = findViewById(R.id.btn_play);
        pause = findViewById(R.id.btn_pause);
        stop = findViewById(R.id.btn_stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        musicUrl = findViewById(R.id.music_url);
        download = findViewById(R.id.btn_download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri= Uri.parse(musicUrl.getText().toString());
                new DownloadMusic().execute(uri);
            }
        });


    }





        private class DownloadMusic extends AsyncTask<Uri, Integer, String> {




            @Override
            protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Uri... uris) {
                downloadData(uris[0],"music.mp3");
                return "Downloading..."; // result
            }
            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                play.setVisibility(View.VISIBLE);
            }

        }

        private void downloadData (Uri uri,  String saveAs) {


            DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            // Create request for android download manager
            DownloadManager.Request request = new DownloadManager.Request(uri);
            //Setting title of request
            request.setTitle("Download "+saveAs);
            //Setting description of request
            request.setDescription("Downloading...");
            // notification download visibility
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            //Set the local destination for the downloaded file to a path within the application's external files directory
            request.setDestinationUri(Uri.parse(Environment.getExternalStorageDirectory().toURI()+"/ThreadsMusic/"+saveAs));
            //Enqueue download and save into downloadReference
            if(permissionGranted()){
                downloadReference = downloadmanager.enqueue(request);
                System.out.println("download refrence "+downloadReference);
            }

        }

        private BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("********* BoradCast Receiver ********* ");
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (downloadReference == id) {
                    System.out.println("==== > download refrence equals == "+downloadReference);
                    Toast.makeText(MainActivity.this, "Download completed", Toast.LENGTH_SHORT).show();
                }
            }
        };

    public  boolean permissionGranted() {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true; // permission granted
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false; // access denied
            }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                if(mediaPlayer == null){
                    Uri myUri = Uri.parse(Environment.getExternalStorageDirectory().toURI()+"/ThreadsMusic/music.mp3");

                    mediaPlayer = MediaPlayer.create(getApplicationContext(),myUri);

                    mediaPlayer.start();
                    pause.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    play.setVisibility(View.GONE);
                } else if(!mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(pauseCurrentPosition);
                    mediaPlayer.start();
                    pause.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    play.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_pause:
                if(mediaPlayer != null){
                    mediaPlayer.pause();
                    pauseCurrentPosition = mediaPlayer.getCurrentPosition();
                    pause.setVisibility(View.GONE);
                    stop.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_stop:
                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    pause.setVisibility(View.GONE);
                    stop.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    mediaPlayer = null;
                }


                break;

        }
    }
}
