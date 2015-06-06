package nozero.apps1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class cListPost extends ActionBarActivity implements View.OnClickListener {

    ListView listview;
    String[] judul;
    String[] isi;
    String[] kategori;
    String[] postid;
    PostAdapter adapter;
    String iR = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_list_post);

        listview = (ListView)findViewById(R.id.list_View);
       // nama = getResources().getStringArray(R.array.nama_reliever);
      //  isi = getResources().getStringArray(R.array.isi_pesan);

        getMyCurhat(iR);
        int i=0;
        adapter = new PostAdapter(getApplicationContext(), R.layout.post);
        listview.setAdapter(adapter);
        for(String relieve : judul){
            PostProvider pesan = new PostProvider(judul[i], isi[i]);
            adapter.add(pesan);
            i++;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent x = new Intent(cListPost.this, cSubmitPost.class);
                //startActivity(x);
                int posisi = i;
                  //  Toast.makeText(getBaseContext(),""+posisi, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(cListPost.this, cCommentarPost.class);

                Bundle jembatan = new Bundle();

                jembatan.putString("judul", judul[posisi]);
                jembatan.putString("isi", isi[posisi]);
                jembatan.putString("kategori", kategori[posisi]);
                jembatan.putString("postid", postid[posisi]);

                intent.putExtras(jembatan);

                startActivity(intent);


            }
        });
    }

    public void getMyCurhat (String iR){ //udah
        //set get


        String url = "http://endpoint-relieve.sundaycode.co/v0/posts?user_id="+iR+"";
        List<NameValuePair> myPair = new ArrayList<NameValuePair>();

        Bridge move = new Bridge(true); move.setMyPair(myPair);
        try {
            move.setResponse(move.execute(url).get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        move.setData(move.getResponse());

        // Toast.makeText(getBaseContext(),""+move.getData(), Toast.LENGTH_LONG).show();



        judul = new String[move.getData().length()]; //
        isi = new String[move.getData().length()];
        kategori = new String[move.getData().length()];
        postid = new String[move.getData().length()];//


        for (int i = 0; i < move.getData().length(); i++) {
            JSONObject myData;
            try {
                myData = move.getData().getJSONObject(i);
                judul[i] = myData.getString("post_title");

                isi[i] = myData.getString("post_content");

                kategori[i] = myData.getString("post_category");
                postid[i] = myData.getString("post_id");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


    }


    @Override
    public void onClick(View v) {
//        Intent i = new Intent(cListPost.this, cSubmitPost.class);
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
