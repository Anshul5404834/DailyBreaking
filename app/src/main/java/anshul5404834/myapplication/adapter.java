package anshul5404834.myapplication;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.startActivity;

public class adapter extends ArrayAdapter {
    private Context mcontext;

    Database db;
    private List<news> news;
    public adapter( Context context,  List<news>news) {
        super(context,0, news);
        this.news=news;
        this.mcontext=context;
    }
    public TextToSpeech tts ;
    Intent web = new Intent(Intent.ACTION_VIEW);
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View m = convertView;
        if (m == null){
            m = LayoutInflater.from(mcontext).inflate(R.layout.raw1,parent,false);
        }
        news a = news.get(position);
        final TextView t1 = (TextView)m.findViewById(R.id.textView1);
        TextView t2 = (TextView)m.findViewById(R.id.textView2);
        TextView t3 = (TextView)m.findViewById(R.id.textView3);
        ImageView i = (ImageView)m.findViewById(R.id.imageView);
        t1.setText(a.getContent());
        t2.setText(a.getDate());
        t3.setText(a.getDescription());
 final String abcd = a.getContent();
      final   String Url = a.getUrl();
        Glide.with(mcontext).load(a.getImage()).apply( new RequestOptions().placeholder(R.drawable.download)).into(i);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                web.setData(Uri.parse(Url));
                mcontext.startActivity(web);
            }
        });


        ImageButton button = m.findViewById(R.id.TTS);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts = new TextToSpeech(mcontext, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status==TextToSpeech.SUCCESS){
                            tts.setLanguage(Locale.US);
                            tts.stop();
                            tts.speak(abcd,TextToSpeech.QUEUE_FLUSH,null,"id1");

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
                            });
                        }
                        if (status==TextToSpeech.ERROR){
                            Intent installIntent = new Intent();
                            installIntent.setAction(
                                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            startActivity(mcontext,installIntent,null);
                        }
                    }
                });

            }
        });
//apply(new RequestOptions().override(600,200).centerCrop().useAnimationPool(true))



        return m;

    }
}

