package com.jesse.mp3downloader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class IncomingLinkActivity extends AppCompatActivity {

    private String title;
    private String artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_link);

        // Get the incoming URL from youtube
        Bundle extras = getIntent().getExtras();
        final String link = extras.getString(Intent.EXTRA_TEXT);

        EditText editTitle = findViewById(R.id.editTitle);
        EditText editArtist = findViewById(R.id.editArtist);

        // Get the title and artist
        RequestHttp request = new RequestHttp(link);
        request.execute();



        try {
            request.get();

            title = request._title;
            artist = request._artist;

            editTitle.setText(title);
            editArtist.setText(artist);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

                // Add the event for the download button
        Button btnDownload = (Button) findViewById(R.id.buttonDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downloadFile(link);
            }
        });

    }

    private void downloadFile(String link)
    {
        System.out.println("DOWNLOAD BUTTON HAS BEEN PRESSED!!");
        try
        {
            link = link.substring(17);
            URL api = new URL("https://t1.youtube6download.top/odg/ec7526980e5e8bbb7b8b244bbf946885c78be35d/" + link);
            DownloadFile downloadFile = new DownloadFile(api, title, artist);
            downloadFile.execute();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
