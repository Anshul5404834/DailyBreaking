package anshul5404834.myapplication;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

@android.arch.persistence.room.Database(entities = news_entity.class,version = 5)
public abstract class Database extends RoomDatabase {
    public abstract NewsDao NewsDao();


}

