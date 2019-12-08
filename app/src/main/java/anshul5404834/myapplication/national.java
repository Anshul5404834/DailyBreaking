package anshul5404834.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.List;

public class national extends AppCompatActivity {
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
         l =(ListView)findViewById(R.id.listview);

       task a = new task(l);

        a.execute("https://newsapi.org/v2/top-headlines?country=in&apiKey=787aa25485844c788c76fba73a6e887a");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}