package anshul5404834.myapplication;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class search extends AppCompatActivity {
    Database db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        setContentView(R.layout.entertainment);
        final ListView l=(ListView)findViewById(R.id.listview);
        db= Room.databaseBuilder(l.getContext(),Database.class,"database_name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        db.NewsDao().getupdated();
        List<news_entity>news_entities= db.NewsDao().getAll();
        l.setAdapter(new adapter2(l.getContext(),news_entities));

    }
}
