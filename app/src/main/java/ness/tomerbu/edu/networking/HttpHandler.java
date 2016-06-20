package ness.tomerbu.edu.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by android on 20/06/2016.
 */
//Requires Uses-Internet-Permission
public class HttpHandler {
    public static String getURL(String address) throws IOException {
        //http://www.google.co.il
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        //how much data are we going to receive:
        int contentLength = con.getContentLength();
        System.out.println("Content Length: " + contentLength);

        //Response code 200 = OK!, 404 not found, 400 Not Authorized, 500 Unknown server error, 429 too much requests
        System.out.println("Response code: "  + con.getResponseCode());
        InputStream in = con.getInputStream();
        return readInputStream(in);
    }

    public static String readInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String total = "";
        String line = reader.readLine();
        while (line!=null){
            total += line;
            total += "\n";
            line = reader.readLine();
        }

        return total;
    }
}
