package nozero.apps1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class activity5 extends ActionBarActivity {

    ListView listview;
    String[] nama;
    String[] isi;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        listview = (ListView)findViewById(R.id.listkomentar);
        nama = getResources().getStringArray(R.array.nama_komentator);
        isi = getResources().getStringArray(R.array.isi_komentar);
        int i=0;
        adapter = new CommentAdapter(getApplicationContext(), R.layout.comment);
        listview.setAdapter(adapter);
        for(String relieve : nama){
            CommentProvider pesan = new CommentProvider(nama[i], isi[i]);
            adapter.add(pesan);
            i++;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity5, menu);
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
