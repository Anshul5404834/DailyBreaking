package anshul5404834.myapplication;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.startActivity;

public class adapter2 extends ArrayAdapter<news_entity> {
    private Context context;
    private List<news_entity>news_entities;
    public adapter2( Context context,List<news_entity> objects) {
        super(context, 0, objects);
        this.context=context;
        this.news_entities=objects;
    }
private TextToSpeech tts;
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View m;
        if(convertView!=null){
            m=convertView;
        }else {
            m=LayoutInflater.from(context).inflate(R.layout.raw1,parent,false);
        }
        news_entity a = news_entities.get(position);
        TextView t1 = (TextView)m.findViewById(R.id.textView1);
        TextView t2 = (TextView)m.findViewById(R.id.textView2);
        TextView t3 = (TextView)m.findViewById(R.id.textView3);
        ImageView i = (ImageView)m.findViewById(R.id.imageView);
        t1.setText(a.getHeading());
        t2.setText(a.getDate_time());
        t3.setText(a.getDescriptions());
        final String abcd = a.getHeading();
i.setImageResource(R.drawable.download);
        ImageButton button = m.findViewById(R.id.TTS);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.US);
                            tts.stop();
                            tts.speak(abcd, TextToSpeech.QUEUE_FLUSH, null, "id1");

                            tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                                @Override
                                public void onStart(String utteranceId) {
                                }

                                @Override
                                public void onDone(String utteranceId) {
                                    tts.shutdown();
                                }

                                @Override
                                public void onError(String utteranceId) {
                                }
                            });
                        }
                        if (status == TextToSpeech.ERROR) {
                            Intent installIntent = new Intent();
                            installIntent.setAction(
                                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            startActivity(context, installIntent, null);
                        }
                    }
                });
            }});

                return m;}}
