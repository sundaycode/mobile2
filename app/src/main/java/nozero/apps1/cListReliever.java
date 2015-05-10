package nozero.apps1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class cListReliever extends ActionBarActivity {

    ListView listview;
    String[] title;
    String[] isi;
    PostAdapter adapter;

    public void inid(ArrayList<Post> x){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aListReliever);

        listview = (ListView)findViewById(R.id.list_v);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            title = extras.getStringArray("title");
            isi = extras.getStringArray("isi");
        }
//        title = getResources().getStringArray(R.array.judul_post);
//        isi = getResources().getStringArray(R.array.isi_post);

        int i=0;
        adapter = new PostAdapter(getApplicationContext(), R.layout.post);
        listview.setAdapter(adapter);
        for(String judul : title){
            PostProvider post = new PostProvider(title[i], isi[i]);
            adapter.add(post);
            i++;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent x = new Intent(cListReliever.this, cSubmitPost.class);
                startActivity(x);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity6, menu);
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
