package nozero.apps1;

/**
 * Created by F on 5/25/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Bridge extends AsyncTask<String, Void, String> {
    private JSONArray data;
    public HttpClient client = cWelcome.client;
    private String response;
    private Boolean isget;
    private List<NameValuePair> myPair;
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        if(isget){return GET(params[0]);}
        else {return POST(params[0]);}
    }

    @Override
    protected void onPostExecute(String result) {
        // Toast.makeText(getBaseContext(),"hasilnya = "+result,Toast.LENGTH_LONG).show();
        super.onPostExecute(result);
    }

    public Bridge(Boolean isget) {
        // TODO Auto-generated constructor stub
        this.isget=isget;
    }

    public List<NameValuePair> getMyPair() {
        return myPair;
    }

    public void setMyPair(List<NameValuePair> myPair) {
        this.myPair = myPair;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(String response) {
        try {
            this.data = new JSONArray(response);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String GET(String url) {
        // TODO Auto-generated method stub
        String result = "";
        try {
            HttpResponse response = client.execute(new HttpGet(url));
            result=getHttpResponse(response);
        } catch (Exception e) {
            Log.d("error", "error:" + e);
            Log.d("error", "result:" + result);
        }
        return result;
    }

    public String POST(String url) {
        // TODO Auto-generated method stub
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(myPair));
            HttpResponse response = client.execute(httpPost);
            result=getHttpResponse(response);

        } catch (Exception e) {
            Log.d("error", "error:" + e);
        }
        Log.d("error", "url:" + url);
        Log.d("error", "result:" + result);
        return result;
    }
    public String getHttpResponse(HttpResponse response){
        InputStream is = null;
        String result = "";
        try {
            is = response.getEntity().getContent();
            if (is != null) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));
                StringBuilder str = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
                is.close();
                result = str.toString();
            } else
                result = "error";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}