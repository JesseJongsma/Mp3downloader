package com.jesse.mp3downloader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.JsonReader;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;

public class DownloadFile extends AsyncTask<Void, Void, Void> {
    private URL _api;
    private String _title, _artist;

    public DownloadFile(URL api, String title, String artist) {
        _api = api;
        _title = title;
        _artist = artist;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int count;
        try {
            URLConnection connection = _api.openConnection();
            connection.connect();
            int lenghtOfFile = connection.getContentLength();
            InputStream input = new BufferedInputStream(_api.openStream());

            // DEBUG
            URI dataDir = Environment.getDataDirectory().toURI();
            System.out.println();
            File f = new File("/");
            File[] files = f.listFiles();
            for (File inFile : files) {
                if (inFile.isDirectory()) {
                    System.out.println(inFile);
                }
            }

            File newFile = new File(Environment.getDataDirectory()+"/Download/"+_title+".mp3");
            System.out.println("line 46");

                newFile.createNewFile();
            System.out.println("line 49");
            OutputStream output = new FileOutputStream(Environment.getDataDirectory()+"/Download/"+_title+".mp3");
            System.out.println("line 51");
            System.out.println(output.toString());
            byte data[] = new byte[1024];

            long total = 0;

//            while ((count = input.read(data)) != -1) {
//                total += count;
//                // publishing the progress....
//                publishProgress((int)(total*100/lenghtOfFile));
                output.write(data);
//            }

            output.flush();
            output.close();
            input.close();
//            System.out.println("line 28");
//            response.setRequestProperty("X-Mashape-Key", "sMRw5zDMqfmsh7u5caw3RBByuKSpp1biF8ljsns5irIZiK5vvP");
//            System.out.println("line 30");
//            response.setRequestProperty("Accept", "text/plain");
//            System.out.println("line 32");
//            if (response.getResponseCode() == 200) {
//                InputStream responseBody = response.getInputStream();
//                System.out.println("line 35");
//                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
//                System.out.println("line 37");
//                JsonReader jsonReader = new JsonReader(responseBodyReader);
//                System.out.println("line 39");
//                ReadJSON(jsonReader);
//                System.out.println("line 41");
//            } else {
//                System.out.println(response.getResponseCode());
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

//    private void ReadJSON(JsonReader jsonReader) {
//        try {
//            jsonReader.beginObject(); // Start processing the JSON object
//            System.out.println("line 54");
//            while (jsonReader.hasNext()) { // Loop through all keys
//                String key = jsonReader.nextName(); // Fetch the next key
//                System.out.println("line 57");
//                if (key.equals("data")) { // Check if desired key
//                    // Fetch the value as a String
//                    while(jsonReader.hasNext())
//                    String data = jsonReader.nextName();
//                    String value = jsonReader.nextString();
//                    System.out.println("line 61");
//                    System.out.println(value);
//                    System.out.println("line 63");
//
//                    break; // Break out of the loop
//                } else {
//                    jsonReader.skipValue(); // Skip values of other keys
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
