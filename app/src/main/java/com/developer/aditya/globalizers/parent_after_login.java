package com.developer.aditya.globalizers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class parent_after_login extends AppCompatActivity {

    ListView listView;
    EditText stdName;
    EditText enrol;
    TextView attendance;
    int attnd;
    String [] numbers = new String[2];
    private static final String dataUrl2 =  " https://murmuring-island-52480.herokuapp.com/getAllTransactions/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_after_login);
    }

    public void callDetailStudentsrc(View view) {

       stdName = (EditText)findViewById(R.id.usernameStudsrc);
       enrol = (EditText)findViewById(R.id.passwordStudsrc);

        setContentView(R.layout.activity_student_detail);
        listView = (ListView)findViewById(R.id.prototype_cell_studentInfo);
       RelativeLayout bt1 = (RelativeLayout) findViewById(R.id.callParent);
       bt1.setEnabled(false);
       bt1.setVisibility(View.GONE);
        RelativeLayout bt2 = (RelativeLayout) findViewById(R.id.callStudent);
        bt2.setEnabled(false);
        bt2.setVisibility(View.GONE);
        attendance = (TextView)findViewById(R.id.studentInfoStats);
        new InternAppAsyncTask().execute(createUrl(dataUrl2));
    }

    public class InternAppAsyncTask extends AsyncTask<URL,Void,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(parent_after_login.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        @Override
        protected String doInBackground(URL... urls) {
            URL url;
            url = createUrl(dataUrl2);
            String jsonResponse = "";
            try{
                jsonResponse = makeHttpRequest(url);
                Log.e("json data-------------", "doInBackground: "+jsonResponse);
            }catch (IOException e){

            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse){
            progressDialog.dismiss();
            if(jsonResponse==null){
                return;
            }
            updateUI(jsonResponse);
        }

    }
    private String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line= reader.readLine();
            }
        }
        return output.toString();
    }
    private String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.e("Respone found : ", "makeHttpRequest: "+jsonResponse );
            }
        }catch (IOException e){
            Log.e("Exception", "makeHttpRequest: ", e.getCause());
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    public URL createUrl(String stringUrl){
        URL url;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e("exception", "createUrl: ",e );
            return null;
        }
        return url;
    }

    public void updateUI(String jsonData) {

//        if (!stud) {
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
//        else{
            HashMap<Integer, MyStudent> myNameHashMap = new HashMap<>();
            ArrayList<MyStudent> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(stdName.getText().toString(),enrol.getText().toString(),jsonData,numbers);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyStudent> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            if(myNameArrayList.size() >0) {
                TextView name = (TextView) findViewById(R.id.studentInfoName);
                name.setText(myNameArrayList.get(0).getName());

                TextView skills = (TextView) findViewById(R.id.studentInfoCourseName);
                skills.setText(myNameArrayList.get(0).getCourse());

                Log.e("Name and Course", "updateUI: " + name + skills);
                attnd = myNameArrayList.size();
                attendance.setText("Total attendance :"+attnd + "/30");
                AdapterClassNew itemAdapter = new AdapterClassNew(this, myNameArrayList);
                listView.setAdapter(itemAdapter);
            }
            else {
                Toast.makeText(this, "No Such Student Found", Toast.LENGTH_SHORT).show();
            }
        }
}
