package anshul5404834.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.List;


public class sports extends AppCompatActivity {
    List<news_entity> news_entities;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
        l =(ListView)findViewById(R.id.listview);
 task a = new task(l);
        a.execute("https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=787aa25485844c788c76fba73a6e887a");
    //    news_entities =a.news_entities;
    }
    public ListView getl(){
        return l;
    }
}
