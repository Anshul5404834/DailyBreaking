package anshul5404834.myapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    DrawerLayout drawer;
    boolean hello ;
    private static final int SPLASH_TIME_OUT=2000;
  public boolean internetconnection(){
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo an =cm.getActiveNetworkInfo();
        hello = an != null &&an.isConnected();
        return hello;
    }

 //  ListView context;
 //  public MainActivity(){}
 //  public MainActivity(ListView listView){
 //      context=listView;
 //  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      if(Build.VERSION.SDK_INT >= 26) {
          AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
          Intent myintent = new Intent(this, notification.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myintent, 0);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
              alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), pendingIntent);
          }
          alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, 1000, AlarmManager.INTERVAL_DAY, pendingIntent);
      }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();
        //            }
        //        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawer.openDrawer(Gravity.LEFT);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    int number;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    entertainment entertainments = new entertainment();
    finance finances = new finance();
    nature natures = new nature();
    sports sportss=new sports();
    technical technicals=new technical();
    world worlds=new world();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.GEOGRAPHICAL) {
            if(internetconnection()){
                Intent i = new Intent(this, nature.class);
                startActivity(i);
            }  else
            {
              // natures.getl().setAdapter(new adapter2(this,news_entities));
                Intent na = new Intent(this, search.class);
                startActivity(na);
                Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.WORLD) {
            if(internetconnection()){
                Intent wo = new Intent(this, world.class);
                startActivity(wo);}else {
             //   news_entities=worlds.news_entities;
             //  worlds.getl().setAdapter(new adapter2(this,news_entities));
                Intent na = new Intent(this, search.class);
                startActivity(na);
                Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.NATIONAL) {
            if(internetconnection()){
                Intent na = new Intent(this, national.class);
                startActivity(na);
                }
                else { Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
                Intent na = new Intent(this, search.class);
                startActivity(na);
              //  news_entities=nationals.getNews_entities();
           //    if(news_entities==null){
           //         Log.w("*******************", "onNavigationItemSelected: #*#*#*##################################*********************@@@@@@@@@@@@@@@@@@@@@@@@@@@###########################%%%%%%%%%%%%%%%^^^^^^^^^^^^^^^^&&&&&&&&&&&&&&&&&**********" );
            //   }
             //   this.setAdapter(new adapter2(context.getContext(),news_entities));
            }
        } else if (id == R.id.FINANCE) {
            if(internetconnection()){
                Intent f = new Intent(this, finance.class);
                startActivity(f);}else {
                Intent na = new Intent(this, search.class);
                startActivity(na);
           //     news_entities=finances.news_entities;
           //    finances.getl().setAdapter(new adapter2(this,news_entities));
                Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();; }
        } else if (id == R.id.SPORTS) {
            if(internetconnection()){
                Intent sp = new Intent(this,sports.class);
                startActivity(sp);}else { Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
                Intent na = new Intent(this, search.class);
                startActivity(na);
           //     news_entities=sportss.news_entities;
           //     sportss.getl().setAdapter(new adapter2(getApplicationContext(),news_entities));
            }} else if (id == R.id.ENTERTAINMENT) {
            if(internetconnection()){
                Intent ent = new Intent(this, entertainment.class);
                startActivity(ent);}else {
        //        news_entities=entertainments.news_entities;
                Intent na = new Intent(this, search.class);
                startActivity(na);
        //        entertainments.getl().setAdapter(new adapter2(this,news_entities));
                Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.TECHNOLOGY) {
            if(internetconnection()){
                Intent t = new Intent(this, technical.class);
                startActivity(t);}else {
            //    news_entities=technicals.news_entities;
                Intent na = new Intent(this, search.class);
                startActivity(na);
            //    technicals.getl().setAdapter(new adapter2(this,news_entities));
                Toast.makeText(this,"No INTERNET CONNECTION",Toast.LENGTH_SHORT).show();
            }

        }else if (id==R.id.PODCAST){
            Intent t = new Intent(this, podcast.class);
            startActivity(t);

        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(Gravity.LEFT);
            return true;
        }return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
