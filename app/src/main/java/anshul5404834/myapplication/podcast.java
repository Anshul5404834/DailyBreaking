package anshul5404834.myapplication;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;
import java.util.Locale;


public class podcast extends AppCompatActivity {
    Database db;
    private TextToSpeech tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podcast);
        Toast.makeText(getApplicationContext(),"Please wait",Toast.LENGTH_SHORT).show();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final ConstraintLayout linearLayout = (ConstraintLayout) findViewById(R.id.podcast_content);
        ImageView imageView =(ImageView)findViewById(R.id.podcast_image);

        final int length =TextToSpeech.getMaxSpeechInputLength();

        db = Room.databaseBuilder(linearLayout.getContext(), Database.class, "database_name").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        List<news_entity> news_entities = db.NewsDao().getheading();
        List<news_entity> news_entities40 = db.NewsDao().getheading40();
        List<news_entity> news_entities30 = db.NewsDao().getheading30();
        StringBuffer data50 = new StringBuffer();
        for (news_entity news_ent: news_entities) {
            final String string = news_ent.getHeading();
            data50.append(string);
            data50.append("..");
        }
        final String data1 = data50.toString();
      int req=  data1.length();

        StringBuffer data40 = new StringBuffer();
        for (news_entity news_ent: news_entities40) {
            final String string = news_ent.getHeading();
            data40.append(string);
            data40.append("..");
        }
        final String data2 = data40.toString();
        int req40=  data2.length();

        StringBuffer data30 = new StringBuffer();
        for (news_entity news_ent: news_entities30) {
            final String string = news_ent.getHeading();
            data40.append(string);
            data40.append("..");
        }
        final String data3 = data30.toString();
        int req30=  data3.length();
        if(req<length){
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.US);
                    tts.getVoice();
                    tts.setSpeechRate(1);
                    tts.speak(data1,TextToSpeech.QUEUE_FLUSH,null,"id1");

                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) { }

                        @Override
                        public void onDone(String utteranceId) {
                            tts.shutdown();
                        }

                        @Override
                        public void onError(String utteranceId) {
                        }
                    }); }

            }
        });
        }
    else if(req40<length){tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.US);
                    tts.getVoice();
                    tts.setSpeechRate(1);
                    tts.speak(data2,TextToSpeech.QUEUE_FLUSH,null,"id1");

                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) { }

                        @Override
                        public void onDone(String utteranceId) {
                            tts.shutdown();
                        }

                        @Override
                        public void onError(String utteranceId) {
                        }
                    }); }

            }
        });}
        else {tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.US);
                    tts.getVoice();
                    tts.setSpeechRate(1);
                    tts.speak(data3,TextToSpeech.QUEUE_FLUSH,null,"id1");

                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) { }

                        @Override
                        public void onDone(String utteranceId) {
                            tts.shutdown();
                        }

                        @Override
                        public void onError(String utteranceId) {
                        }
                    }); }
            }
        });}



    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.shutdown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tts.shutdown();
    }

    public void onclick(View view) {
        Snackbar.make(view,"Load other Contents to update the podcast",
                Snackbar.LENGTH_SHORT).show();
    }
}
