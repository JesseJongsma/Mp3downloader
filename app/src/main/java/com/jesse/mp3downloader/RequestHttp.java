package com.jesse.mp3downloader;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestHttp extends AsyncTask<Void,Void, Void>
{
    private String _url = "";
    public String _title = "";
    public String _artist = "";

    public RequestHttp(String url)
    {
        _url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        getInformation(_url);
        return null;
    }

    private void getInformation(String url)
    {
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements scripts = doc.select("script");
            Pattern artistPattern = Pattern.compile("st\"..\"contents\"...\"simpleText\":\"(.+?)\"");
            Pattern titlePattern = Pattern.compile(".....\"..\"contents\"...\"simpleText\":\"Forgoy(.+?)\"");
            Matcher artistMatcher = artistPattern.matcher(scripts.html());
            Matcher titleMatcher = titlePattern.matcher(scripts.html());
            artistMatcher.find();
            titleMatcher.find();

            _artist = artistMatcher.group().substring(32, artistMatcher.group().length() - 1);
            _title = titleMatcher.group().substring(35, titleMatcher.group().length() - 1);

        }
        catch(Exception e)
        {
            System.out.println("Error coming up!");
            System.out.println(e);
        }
    }
}
