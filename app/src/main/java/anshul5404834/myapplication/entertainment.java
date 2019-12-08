package anshul5404834.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.List;

public class entertainment extends AppCompatActivity {
    List<news_entity> news_entities;
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
        ListView l = (ListView) findViewById(R.id.listview);
        task a = new task(l);
        a.execute("https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=787aa25485844c788c76fba73a6e887a");
       // news_entities = a.news_entities;
    }}


//  <application
//    android:allowBackup="true"
//    android:icon="@mipmap/ic_launcher"
//    android:label="@string/app_name"
//    android:roundIcon="@mipmap/ic_launcher_round"
//    android:supportsRtl="true"
//    android:theme="@style/AppTheme">
//    <activity
//        android:name=".MainActivity"
//        android:label="@string/app_name"
//        android:theme="@style/AppTheme.NoActionBar">
//        <intent-filter>
//            <action android:name="android.intent.action.MAIN" />
//
//            <category android:name="android.intent.category.LAUNCHER" />
//        </intent-filter>
//    </activity>
//</application>