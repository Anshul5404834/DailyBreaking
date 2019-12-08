package anshul5404834.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class task extends AsyncTask<String,String,List<news>> {
    HttpURLConnection connection;
    URL url;
    ListView l ;
    InputStream stream = null;

    StringBuffer data;
    List<news> news = new ArrayList<>();
    List<news_entity>news_entities =new ArrayList<news_entity>();
   public task(ListView listView){
        this.l=listView;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
     //   ViewGroup view = (ViewGroup) l.findViewById(android.R.id.content);
      //  View view2= LayoutInflater.from(l.getContext()).inflate(R.layout.offline,view);
        Toast.makeText(l.getContext(),"PLEASE WAIT",Toast.LENGTH_LONG).show();

    }

    Database  db;
    @Override
    protected List<news> doInBackground(String... strings) {
         db= Room.databaseBuilder(l.getContext(),Database.class,"database_name").fallbackToDestructiveMigration().build();
         try {
             try {
                 url = new URL(strings[0]);
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             }
             try {
                 connection = (HttpURLConnection) url.openConnection();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 connection.connect();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             try {
                 stream = connection.getInputStream();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             BufferedReader reader;
             reader = new BufferedReader(new InputStreamReader(stream));

             data = new StringBuffer();
             String line;
             try {
                 while ((line = reader.readLine()) != null) {
                     data.append(line);
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             {
                 try {
                     JSONObject parentobject;
                     JSONArray parentarray;
                     parentobject = new JSONObject(data.toString());
                     parentarray = parentobject.getJSONArray("articles");
                     for (int i = 0; i < parentarray.length(); i++) {
                         JSONObject mainblocks = parentarray.getJSONObject(i);
                         news.add(new news(mainblocks.optString("title").toString(), mainblocks.optString("description").toString(),
                                 mainblocks.optString("publishedAt").toString(), mainblocks.optString("urlToImage").toString(),
                                 mainblocks.optString("url")));


                         //     // for dao
                         String Local = mainblocks.optString("publishedAt");
                         int b = Local.indexOf('T');
                         String date1 = Local.substring(0, b);
                         news_entity a = new news_entity();
                         a.setDate_time(date1);
                         a.setHeading(mainblocks.optString("title"));
                         a.setDescriptions(mainblocks.optString("description"));
                         news_entities.add(a);
                         //             // back to normal
                         //
                     }
                     db.NewsDao().addNewsList(news_entities);
                     news_entities = db.NewsDao().getAll();
                 } catch (JSONException e) {
                     e.printStackTrace();
                 } finally {
                     if (connection != null) {
                         connection.disconnect();
                     }
                     if (reader != null) {
                         try {
                             reader.close();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                 }

             }

         }catch (Exception e){}

        return news;

    }

    Intent web = new Intent(Intent.ACTION_VIEW);
    private static final int TTS_REQUEST_DATA =101;

    @Override
    protected void onPostExecute(final List<news> news) {
        super.onPostExecute(news);



        final adapter a =  new adapter(l.getContext(),news);
        l.setAdapter(a);
        l.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                return false;
            }
        });
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Context L = l.getContext();

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                news a = news.get(position);
                String Url = a.getUrl();
                web.setData(Uri.parse(Url));
                L.startActivity(web);
              //
             //   db = Room.databaseBuilder(L,Database.class,"database_name").build();
              //  db.NewsDao().addNewsList(news_entities);
            }
        });

    }
// public List<news_entity> check_room(){
//  news_entities = db.NewsDao().getAll();
//  if (news_entities==null){
//      Log.d("@@@@@@@@@@@@@", "check_room: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +
//              "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//
//  }
//     return news_entities;

   // tts = new TextToSpeech(l.getContext(), new TextToSpeech.OnInitListener() {
   //     @Override
   //     public void onInit(int status) {
   //         if (status==TextToSpeech.SUCCESS){
   //             tts.setLanguage(Locale.US);
   //             tts.speak(l.toString(),TextToSpeech.QUEUE_FLUSH,null,"id1");
   //         }
   //     }
   // });
//
//  }
}
