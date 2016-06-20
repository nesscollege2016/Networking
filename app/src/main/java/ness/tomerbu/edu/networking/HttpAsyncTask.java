package ness.tomerbu.edu.networking;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by android on 20/06/2016.
 */
public class HttpAsyncTask extends AsyncTask<String, Integer, String>{

    TextView tvResult;
    public HttpAsyncTask(TextView tvResult) {
        this.tvResult = tvResult;
    }

    @Override
    protected String doInBackground(String... addresses) {
        String result = null;
        try {
            result = HttpHandler.getURL(addresses[0]);
            JSONObject weatherJSON = new JSONObject(result);
            JSONObject mainJSON = weatherJSON.getJSONObject("main");
            Double temp = mainJSON.getDouble("temp");
            result = (temp - 273) + "Celsius";

        } catch (IOException e) {
            e.printStackTrace();
            result = "Error Connecting to Server";
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        tvResult.setText(result);
    }
}