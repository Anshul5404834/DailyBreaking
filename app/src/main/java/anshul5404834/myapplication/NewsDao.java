package anshul5404834.myapplication;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.widget.ListView;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    public void addNews(news_entity news_entity);

    @Insert
    public void addNewsList(List<news_entity>news_entities);

    @Query("SELECT heading,pk FROM news_entity ORDER BY pk DESC LIMIT 50")
    List<news_entity>getheading();

    @Query("SELECT heading,pk FROM news_entity ORDER BY pk DESC LIMIT 40")
    List<news_entity>getheading40();
    @Query("SELECT heading,pk FROM news_entity ORDER BY pk DESC LIMIT 30")
    List<news_entity>getheading30();


    @Query("SELECT * FROM news_entity ORDER BY pk DESC LIMIT 80")
    List<news_entity>  getAll();

    @Query("DELETE FROM news_entity where pk NOT IN (SELECT pk from news_entity ORDER BY pk DESC LIMIT 80)")
    void getupdated();
}
//