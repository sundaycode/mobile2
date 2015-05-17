package nozero.apps1;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ramadhanario on 10/05/2015.
 */
public class Bridge extends AsyncTask<String, Void, String> {

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

    @Override
    protected String doInBackground(String... params) {
        try {
//                    makeRequest("http://relieve-endpoint.herokuapp.com/v0/posts",tes);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://relieve-endpoint.herokuapp.com/v0/users");
            JSONObject holder = getJsonObjectFromMap(params);

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
            Log.d("error", "error :" + e);
        }
        return null;
    }
}
