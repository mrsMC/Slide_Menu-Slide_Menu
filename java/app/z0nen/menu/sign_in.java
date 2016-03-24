package app.z0nen.menu;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class sign_in extends AsyncTask<String, Void, String> {

    public TextView oilLevel;
    public Context context;
    private int byGetOrPost = 0;//flag 0 means get and 1 means post.(By default it is get.)
    public String oilLevelFromDataBase;

    public sign_in(Context context, TextView oilLevel, int flag) {
        this.context = context;
        this.oilLevel = oilLevel;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        if (byGetOrPost == 1) { //means by Post Method
            try {
                String email = arg0[0];
                String password = arg0[1];
                String link = "http://82.141.235.157:50010/mApp/oilReadingTimestamp.php";
                String data = URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8")
                        + "=" + URLEncoder.encode(password, "UTF-8");


                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        } else return null;
    }

    @Override
    protected void onPostExecute(String result) {

        this.oilLevel.setText(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        //  this.oilLevel.setText(values);

    }

}
