package com.developer.aditya.globalizers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GlobalizersAsyncTask extends AsyncTask<URL,Void,String> {

    ProgressDialog progressDialog;
    Context context;
    String dataUrl;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL url = null;
        url = createUrl(dataUrl);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
            Log.e("json data-------------", "doInBackground: " + jsonResponse);
        } catch (IOException e) {
            Log.e("Url", "doInBackground: " + url);

        }
        return jsonResponse;
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        //  progressDialog.dismiss();
        if (jsonResponse == null) {
            return;
        }
    //    updateUI(jsonResponse);
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.e("Respone found : ", "makeHttpRequest: " + jsonResponse);
            }
        } catch (IOException e) {
            Log.e("Exception", "makeHttpRequest: " + url, e.getCause());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("exception", "createUrl: ", e);
            return null;
        }
        return url;
    }

    public ArrayList<MyDue> sort1(ArrayList<MyDue> A) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.size() - i - 1; j++) {
                try {
                    Date date1 = format.parse(A.get(j).getDate());
                    Date date2 = format.parse(A.get(j + 1).getDate());
                    if (date1.after(date2)) {
                        MyDue temp;
                        temp = A.get(j);
                        A.set(j, A.get(j + 1));
                        A.set(j + 1, temp);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<MyDue> B = new ArrayList<MyDue>();
        B = A;
        return B;
    }

    public ArrayList<MyStats> sort2(ArrayList<MyStats> A) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.size() - i - 1; j++) {
                try {
                    Date date1 = format.parse(A.get(j).getDate());
                    Date date2 = format.parse(A.get(j + 1).getDate());
                    if (date2.after(date1)) {
                        MyStats temp;
                        temp = A.get(j);
                        A.set(j, A.get(j + 1));
                        A.set(j + 1, temp);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<MyStats> B = new ArrayList<MyStats>();
        B = A;
        return B;
    }

//    public void updateUI(String jsonData) {
//            HashMap<Integer, MyName> myNameHashMap = new HashMap<>();
//            ArrayList<MyName> myNameArrayList = new ArrayList<>();
//            myNameHashMap = QueryUtils.extractRecord(jsonData, course);
//            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
//            for (Map.Entry<Integer, MyName> eachName : myNameHashMap.entrySet()) {
//                myNameArrayList.add(eachName.getValue());
//            }
//            AdapterClass itemAdapter = new AdapterClass(this, myNameArrayList);
//            listView.setAdapter(itemAdapter);
//        }
}

