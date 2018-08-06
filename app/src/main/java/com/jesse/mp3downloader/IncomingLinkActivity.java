package com.jesse.mp3downloader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IncomingLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_link);

        // Get the incoming URL from youtube
        Bundle extras = getIntent().getExtras();
        final String url = extras.getString(Intent.EXTRA_TEXT);

        EditText editTitle = findViewById(R.id.editTitle);
        EditText editArtist = findViewById(R.id.editArtist);

        // Get the title and artist
        RequestHttp request = new RequestHttp(url);
        request.execute();
        try {
            request.get();
            editTitle.setText(request._title);
            editArtist.setText(request._artist);
        }catch (Exception e)
        {
            System.out.println(e);
        }

        // Add the event for the download button
        Button btnDownload = (Button) findViewById(R.id.buttonDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downloadFile(url);
            }
        });

    }

    private void downloadFile(String url)
    {
        System.out.println(url);
    }

}
