package ness.tomerbu.edu.networking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView tvResult;
    ImageView ivPicasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvResult = (TextView) findViewById(R.id.tvResult);
        ivPicasso = (ImageView) findViewById(R.id.myWebImage);
        spinner = (Spinner) findViewById(R.id.citySpinner);

        Picasso.with(this).
                load("http://i.imgur.com/gInfxuE.jpg").
                error(R.mipmap.ic_launcher).
                placeholder(R.mipmap.ic_launcher).
                into(ivPicasso);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String city = "";
                switch (position){
                    case 0: //beersheba
                        city = "beersheba";
                        break;
                    case 1:
                        city = "TelAviv";
                        break;
                    case 2:
                        city = "Ashdod";
                        break;
                    case 3:
                        city = "Jerusalem";
                        break;
                }

               String url  = "http://api.openweathermap.org/data/2.5/weather?q="
                       +
                       city +
                       ",IL&appid=ad0f66eb2043ed8fa4fe2789fadd6fc9";
                new HttpAsyncTask(tvResult).execute(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        new HttpAsyncTask(tvResult).
                execute("http://api.openweathermap.org/data/2.5/weather?q=Beersheba,IL&appid=ad0f66eb2043ed8fa4fe2789fadd6fc9");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
