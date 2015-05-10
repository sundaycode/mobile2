package nozero.apps1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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

                Intent i = new Intent(cSubmitPost.this, cCommentarPost.class);
                i.putExtra("title", title);
                i.putExtra("isi", isi);
                startActivity(i);

                HashMap tes = new HashMap();
                tes.put(new String("post_user_id"),"1");
                tes.put(new String("post_psikolog_id"),"1");
                tes.put(new String("post_title"),title[0]);
                tes.put(new String("post_category"),"testes");
                tes.put(new String("post_content"),isi[0]);
                tes.put(new String("post_image_url"),"1234");
                try {
//                    makeRequest("http://relieve-endpoint.herokuapp.com/v0/posts",tes);
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://relieve-endpoint.herokuapp.com/v0/posts");
                    JSONObject holder = getJsonObjectFromMap(tes);

                    //passes the results to a string builder/entity
                    StringEntity se = new StringEntity(holder.toString());

                    //sets the post request as the resulting string
                    httpPost.setEntity(se);
                    //sets a request header so the page receving the request
                    //will know what to do with it
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");
                    HttpResponse response = httpClient.execute(httpPost);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext())
        {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry)iter.next();

            //creates a key for Map
            String key = (String)pairs.getKey();

            //Create a new map
            String m = (String) pairs.getValue();

            //object for storing Json
//            JSONObject data = new JSONObject();

            //gets the value
//            Iterator iter2 = m.entrySet().iterator();
//            while (iter2.hasNext())
//            {
//                Map.Entry pairs2 = (Map.Entry)iter2.next();
//                data.put((String)pairs2.getKey(), (String)pairs2.getValue());
//                System.out.println((String)pairs2.getKey()+"");
//                System.out.println((String)pairs2.getValue()+"");
//            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, m);
        }
        return holder;
    }

    public static HttpResponse makeRequest(String path, Map params) throws JSONException, IOException {
        //instantiates httpclient to make request
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //url with the post data
        HttpPost httpost = new HttpPost(path);

        //convert parameters into JSON object
        JSONObject holder = getJsonObjectFromMap(params);

        //passes the results to a string builder/entity
        StringEntity se = new StringEntity(holder.toString());

        //sets the post request as the resulting string
        httpost.setEntity(se);
        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();

        return (HttpResponse)httpclient.execute(httpost, responseHandler);
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
