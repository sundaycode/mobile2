package nozero.apps1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class cSubmitPost extends ActionBarActivity {

    public static ArrayList<Post> posting;
    String[] title = new String[0];
    String[] isi = new String[0];
    String[] x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_submit_post);

        title = getResources().getStringArray(R.array.judul_post);
        isi = getResources().getStringArray(R.array.isi_post);

        Button go = (Button) findViewById(R.id.bSubmitPost);

        go.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText e1 = (EditText) findViewById(R.id.eTitlePost);
                EditText e2 = (EditText) findViewById(R.id.eContentPost);
                x = title;
                title = null;
                title = addArray(x, e1.getText().toString());
                x = null;
                x = isi;
                isi = null;
                isi = addArray(x, e2.getText().toString());
                x = null;

                Intent i = new Intent(cSubmitPost.this, cListReliever.class);
                i.putExtra("title", title);
                i.putExtra("isi", isi);
                startActivity(i);
            }
        });
    }

    public String[] addArray(String[] stack, String adding){
        String[] temp;
        temp = new String[stack.length+1];

        for(int i=0; i<stack.length; i++){
            temp[i] = stack[i];
        }
        temp[stack.length] = adding;

        return temp;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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
