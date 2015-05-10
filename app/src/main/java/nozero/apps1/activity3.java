package nozero.apps1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class activity3 extends ActionBarActivity implements View.OnClickListener {

    ListView listview;
    String[] nama = new String[0];
    String[] isi = new String[0];
    MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        listview = (ListView)findViewById(R.id.list_View);
        nama = getResources().getStringArray(R.array.nama_reliever);
        isi = getResources().getStringArray(R.array.isi_pesan);

        int i=0;
        adapter = new MessageAdapter(getApplicationContext(), R.layout.message);
        listview.setAdapter(adapter);
        for(String relieve : nama){
            MessageProvider pesan = new MessageProvider(nama[i], isi[i]);
            adapter.add(pesan);
            i++;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent x = new Intent(activity3.this, activity2.class);
                startActivity(x);
            }
        });
    }

    @Override
    public void onClick(View v) {
//        Intent i = new Intent(activity3.this, activity2.class);
//        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity3, menu);
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
