package nozero.apps1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import org.apache.http.message.BasicNameValuePair;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class cDataReliever extends ActionBarActivity {

    String idUser="11";
    String idReliver="1";
    String[] data;
    String[] namaReliver;
    String[] aboutReliver;

    Button wisdom;
    TextView jumlahwisdom;
    TextView namaReliver2;
    EditText aboutReliver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_data_reliever);
        jumlahWisdom(idReliver);
        tombolWisdom();
        inforeliver(idReliver);
        if(cekWisdom(idUser,idReliver)){
            wisdom.setVisibility(View.INVISIBLE);
        }




    }
    //belooommmmm
    public void addWisdom(String iU, String iR){ //belom
        String url = "http://endpoint-relieve.sundaycode.co/v0/wisdom";

        List<NameValuePair> myPair = new ArrayList<NameValuePair>();
        myPair.add(new BasicNameValuePair("user_id",iU));
        myPair.add(new BasicNameValuePair("psikolog_id",iR));


        Bridge move = new Bridge(true);

        move.setMyPair(myPair);
        try {
            move.setResponse(move.execute(url).get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void jumlahWisdom(String iR){ //belum sent get
        // get
        jumlahwisdom = (TextView)findViewById(R.id.jumlahWisdom);
        String jumlah="0";
        String url = "http://endpoint-relieve.sundaycode.co/v0/wisdom?psikolog_id="+iR+"";

        List<NameValuePair> myPair = new ArrayList<NameValuePair>();

        Bridge move = new Bridge(true);
        move.setMyPair(myPair);

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
        data = new String[move.getData().length()];

        for (int i = 0; i < move.getData().length(); i++) {
            JSONObject myData;
            try {
                myData = move.getData().getJSONObject(i);
                data[i] = myData.getString("psikolog_wisdom_point");
                jumlah = data[i];

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        jumlahwisdom.setText(jumlah);

    }
    public void jumlahWisdom2(String iR){// belom server
        // set get
        jumlahwisdom = (TextView)findViewById(R.id.jumlahWisdom);
        String jumlah="0";
        String url = "http://endpoint-relieve.sundaycode.co/v0/wisdom";

        List<NameValuePair> myPair = new ArrayList<NameValuePair>();
        myPair.add(new BasicNameValuePair("psikolog_id", iR));


        Bridge move = new Bridge(true);
        move.setMyPair(myPair);
        Log.d("error", "result:" + move.toString());
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

        data = new String[move.getData().length()];

        for (int i = 0; i < move.getData().length(); i++) {
            JSONObject myData;
            try {
                myData = move.getData().getJSONObject(i);
                data[i] = myData.getString("psikolog_wisdom_point");
                Toast.makeText(getBaseContext(), data[i], Toast.LENGTH_LONG)
                        .show();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        jumlahwisdom.setText(jumlah);

    }
    public void tombolWisdom(){
        final Context context = this;
        wisdom = (Button)findViewById(R.id.tombolWisdom);
        wisdom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            // TODO Auto-generated method stub
           addWisdom(idUser,idReliver);
                if(cekWisdom(idUser,idReliver)){
                    wisdom.setVisibility(View.INVISIBLE);
                    jumlahWisdom(idReliver);
                }
            }
        }
        );

    }

// cekWisdom ok
    public boolean cekWisdom(String iU, String iR){ //bisa
        //set get
        boolean hasil=false;
        String url = "http://endpoint-relieve.sundaycode.co/v0/checkwisdom";

        List<NameValuePair> myPair = new ArrayList<NameValuePair>();
        myPair.add(new BasicNameValuePair("psikolog_id", iR));
        myPair.add(new BasicNameValuePair("user_id", iU));

        Bridge move = new Bridge(false);
        move.setMyPair(myPair);
        Log.d("error", "result:" + move.toString());
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
        Toast.makeText(getBaseContext(),""+move.getData(), Toast.LENGTH_LONG).show();
        /*
        data = new String[move.getData().length()];

        for (int i = 0; i < move.getData().length(); i++) {
            JSONObject myData;
            try {
                myData = move.getData().getJSONObject(i);
                data[i] = myData.getString("wisdom_point_status");
                if(data[i].equals("true")){
                    hasil = true;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
*/
        return hasil;
    }

    public void inforeliver (String iR){ //udah
        //set get
        namaReliver2 = (TextView)findViewById(R.id.namaReliver);
       aboutReliver2 = (EditText)findViewById(R.id.editText4);
        String nama="";
        String about="";

        String url = "http://endpoint-relieve.sundaycode.co/v0/reliever?reliever_id="+iR+"";
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



        namaReliver = new String[move.getData().length()]; //
        aboutReliver = new String[move.getData().length()]; //


        for (int i = 0; i < move.getData().length(); i++) {
            JSONObject myData;
            try {
                myData = move.getData().getJSONObject(i);
                namaReliver[i] = myData.getString("reliever_name");
                nama = namaReliver[i];
                aboutReliver[i] = myData.getString("reliever_bio");
                about = aboutReliver[i];

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        namaReliver2.setText(nama);
        aboutReliver2.setText(about);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity4, menu);
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
