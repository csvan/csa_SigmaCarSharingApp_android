package sigma.scsapp.remote;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class request {

    // TODO Build the connetion to the database

    private static final String TAG = request.class.getSimpleName();

    public request() {
    }

    // Make a request.
    public String requestURL(String reqUrl) {
        String response = null;
        try {
            // 1 Save URL
            URL url = new URL(reqUrl);
            Log.i(TAG, "requesting URL from: " + reqUrl + "1");
            // 2 Create connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3 call "connection"
            conn.setRequestMethod("GET");

            // 4 read stuff from bufferedInputstream
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);


        }
        // Error
        catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        Log.i(TAG, "requesting response from URL: " + response + "2");

        return response;
    }

    private String convertStreamToString(InputStream is) {
        // 1 Create a reader (in).
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // 2 Create a builder
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            // 3 for each line read and add it.
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 5 return it, almost like a "document" in server formatter.
        return sb.toString();
    }
}