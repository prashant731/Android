package com.developer.aditya.globalizers;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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

public class adminPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public String course = null;
    public boolean stud = false;
    TextView studName;
    TextView studId;
    String studNameS, studIdS;
    String[] numbers;
    boolean drawME = false;
    boolean statsShow = false;
    boolean dueShow = false;
    String name;
    String num;
    String courseDue;
    boolean courseClicked=false;
    boolean dueClicked = false;
    boolean url3 = false,url1=false,url2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openStats(MenuItem item) {
//        Intent intent = new Intent(adminPage.this, Stats.class);
//        startActivity(intent);
        drawME=true;
        statsShow = true;
//        url1=false;
//        url2=false;
//        url3 = true;
        onCreat4();
    }

    public void openDue(MenuItem item) {
//        Intent intent = new Intent(adminPage.this, Due.class);
//        startActivity(intent);
        drawME = true;
        dueShow = true;
//        url1=false;
//        url2=false;
//        url3 = true;
        onCreat3();
    }

    public void callActivityNew(String n,String m) {
        numbers = new String[2];
                url1=false;
        url3=false;
        url2 = true;
        courseClicked = true;
        name = n;
        num = m;
        setContentView(R.layout.activity_course_clicked_student_profile);
        listView = (ListView)findViewById(R.id.prototype_cell_studentInfo);
        new InternAppAsyncTask().execute(createUrl(dataUrl2));
       // Toast.makeText(adminPage.this,"Temporary Removed",Toast.LENGTH_SHORT).show();
    }

    public void callActivityNewDue(String m,String n,String o) {
        numbers = new String[2];
        url1=false;
        url3=false;
        url2 = true;
        dueClicked = true;
        name = m;
        courseDue = o;
        setContentView(R.layout.activity_course_clicked_student_profile);
        listView = (ListView)findViewById(R.id.prototype_cell_studentInfo);
        new InternAppAsyncTask().execute(createUrl(dataUrl2));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new section1();
                case 1:
                    return new section2();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "STUDENT";
            }
            return null;
        }
    }

    public void callDetailGRE(View view) {
        stud = false;
        course = "GRE";
        onCreat("GRE");
        System.out.println(view.getId());
    }

    public void callDetailGMAT(View view) {
        stud = false;
        course = "GMAT";
        onCreat("GMAT");
        System.out.println(view.getId());
    }

    public void callDetailSAT(View view) {
        stud = false;
        onCreat("SAT");
        course = "SAT";
        System.out.println(view.getId());
    }

    public void callDetailTOEFL(View view) {
        stud = false;
        course = "TOEFL";
        onCreat("TOEFL");
        System.out.println(view.getId());
    }

    public void callDetailCOURSEZ(View view) {
        stud = false;
        course = "Course";
        onCreat("COURSEZ");
        System.out.println(view.getId());
    }

    public void callDetailCOURSEY(View view) {
        stud = false;
        course = "Course";
        onCreat("COURSEY");
        System.out.println(view.getId());
    }

    public void callDetailCOURSEX(View view) {
        stud = false;
        course = "Course";
        onCreat("COURSEX");
        System.out.println(view.getId());
    }

    public void callDetailStudent(View view) {
       // int id = view.getId();
        stud = true;
        numbers = new String[2];
        //listView = (ListView)findViewById(R.id.prototype_cell);

        TextView studName = (TextView) findViewById(R.id.usernameStud);
        TextView studId = (TextView) findViewById(R.id.passwordStud);
        studNameS = studName.getText().toString();
        studIdS = studId.getText().toString();
        Log.e("Boolean variable stud", "callDetailStudent: " + stud);
        onCreat2();
    }

    public void makeCallParent(View view) {
        try {
            if (numbers.length > 0 && !numbers[1].equalsIgnoreCase("0")) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numbers[1]));
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            } else if (numbers[1].equalsIgnoreCase("0")) {
                Toast.makeText(this, "Parent Number Not Found", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Parent Number Not Found", Toast.LENGTH_SHORT).show();
            Log.e("Exception at", "makeCallParent: "+e.getMessage() );
        }

    }

    public void makeCallStudent(View view) {
        try {
            if (numbers.length > 0 && !numbers[0].equalsIgnoreCase("0")) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numbers[0]));
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            } else if (numbers[0].equalsIgnoreCase("0")) {
                Toast.makeText(this, "Student Number Not Found", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Student Number Not Found", Toast.LENGTH_SHORT).show();
            Log.e("Exception at ", "makeCallStudent: "+e.getMessage() );
        }
    }

    private static final String dataUrl = " https://murmuring-island-52480.herokuapp.com/getAllStudents/";
    private static final String dataUrl2 =  " https://murmuring-island-52480.herokuapp.com/getAllTransactions/";
    private static final String dataUrl3 = "https://murmuring-island-52480.herokuapp.com/Counter";
    ListView listView;

    public void onCreat(String COURSE) {
        Log.e("On create 1 with url", "onCreat: "+dataUrl );
        setContentView(R.layout.fragment_detail);
        TextView heading = (TextView) findViewById(R.id.head);
        heading.setText(COURSE);
        listView = (ListView)findViewById(R.id.prototype_cell);
        url1=true;
        url2=false;
        url3 = false;
//        GlobalizersAsyncTask globalizersAsyncTask = new GlobalizersAsyncTask();
//        globalizersAsyncTask.context = adminPage.this;
//        globalizersAsyncTask.dataUrl = dataUrl;
        new InternAppAsyncTask().execute(createUrl(dataUrl));
    }

    public void onCreat2() {
        stud = true;
        Log.e("On create 2 with url", "onCreat2: "+dataUrl2 );
        setContentView(R.layout.activity_student_detail);
        listView = (ListView)findViewById(R.id.prototype_cell_studentInfo);
        url1=false;
        url2=true;
        url3 = false;
        new InternAppAsyncTask().execute(createUrl(dataUrl2));
    }

    public void onCreat3() {
        Log.e("On create 3 with url", "onCreat3: "+dataUrl );
        setContentView(R.layout.activity_due);
        listView = (ListView)findViewById(R.id.dueList);
        url1=true;
        url2=false;
        url3 = false;
        new InternAppAsyncTask().execute(createUrl(dataUrl));
    }

    public void onCreat4() {
        Log.e("On create 4 with url", "onCreat4: "+dataUrl3 );
        setContentView(R.layout.activity_stats);
        listView = (ListView)findViewById(R.id.stateList);
        url1=false;
        url2=false;
        url3 = true;
        new InternAppAsyncTask().execute(createUrl(dataUrl3));
    }


    public class InternAppAsyncTask extends AsyncTask<URL,Void,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(adminPage.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        @Override
        protected String doInBackground(URL... urls) {
            URL url = null;
            if(url1){
                url = createUrl(dataUrl);
                //url1=false;
            }
            else if(url3)
            {
                url = createUrl(dataUrl3);
              //  url3=false;
            }
            else if(url2)
            {
                url = createUrl(dataUrl2);
               // url2=false;
            }

           // ListView lv = (ListView)findViewById(R.id.)
            String jsonResponse = "";
            try{
                jsonResponse = makeHttpRequest(url);
                Log.e("json data-------------", "doInBackground: "+jsonResponse + url);
            }catch (IOException e){
                Log.e("Url3", "doInBackground: "+url );

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
            Log.e("Exception", "makeHttpRequest: " + url, e.getCause());
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

    public ArrayList<MyDue> sort1(ArrayList<MyDue> A)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for(int i=0;i<A.size();i++) {
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
            B=A;
            return B;
    }

    public ArrayList<MyStats> sort2(ArrayList<MyStats> A)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for(int i=0;i<A.size();i++) {
            for (int j = 0; j < A.size() - i -1; j++) {
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
        B=A;
        return B;
    }

    public void updateUI(String jsonData) {

        if (!stud) {
            HashMap<Integer, MyName> myNameHashMap = new HashMap<>();
            final ArrayList<MyName> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(jsonData, course);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyName> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            AdapterClass itemAdapter = new AdapterClass(this, myNameArrayList);
            listView.setAdapter(itemAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyName name = myNameArrayList.get(position);
                    Log.e("----clicked---", "onItemClick: "+name.getName()+" "+name.getNumber());
                    Toast.makeText(adminPage.this,name.getName() + " " + name.getNumber(),Toast.LENGTH_SHORT).show();
                    callActivityNew(name.getName(),name.getNumber());
                }
            });
        }
        else{
            HashMap<Integer, MyStudent> myNameHashMap = new HashMap<>();
            ArrayList<MyStudent> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(studNameS,studIdS,jsonData,numbers);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyStudent> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            if(myNameArrayList.size() >0) {
                TextView name = (TextView) findViewById(R.id.studentInfoName);
                name.setText(myNameArrayList.get(0).getName());

                TextView skills = (TextView) findViewById(R.id.studentInfoCourseName);
                skills.setText(myNameArrayList.get(0).getCourse());

                TextView attendance = (TextView)findViewById(R.id.studentInfoStats);
                attendance.setText("Total attendance : "+myNameArrayList.size() + "/30");

                Log.e("Name and Course", "updateUI: " + name + skills);
                AdapterClassNew itemAdapter = new AdapterClassNew(this, myNameArrayList);
                listView.setAdapter(itemAdapter);
            }
            else {
                Toast.makeText(this, "No Such Student Found", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_admin_page);
            }
        }

        if(drawME && dueShow)
        {
            drawME = false;
            dueShow = false;
            HashMap<Integer, MyDue> myNameHashMap = new HashMap<>();
            final ArrayList<MyDue> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(jsonData);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyDue> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            ArrayList<MyDue> farrList = sort1(myNameArrayList);
            AdapterClassDue itemAdapter = new AdapterClassDue(this, farrList);
            listView.setAdapter(itemAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyDue name = myNameArrayList.get(position);
                    Log.e("----clicked---", "onItemClick: "+name.getName()+" "+name.getDate() + " "+name.getCourse());
                    Toast.makeText(adminPage.this,name.getName() + " " + name.getDate(),Toast.LENGTH_SHORT).show();
                    callActivityNewDue(name.getName(),name.getDate(),name.getCourse());
                }
            });
        }
        else if(drawME && statsShow)
        {
            drawME = false;
            statsShow = false;
            HashMap<Integer, MyStats> myNameHashMap = new HashMap<>();
            ArrayList<MyStats> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(jsonData,drawME);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyStats> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }

            ArrayList<MyStats> farrList = sort2(myNameArrayList);
            AdapterClassStats itemAdapter = new AdapterClassStats(this, farrList);
            listView.setAdapter(itemAdapter);
        }
        else if(courseClicked)
        {
            courseClicked  = false;
            HashMap<Integer, MyStudent> myNameHashMap = new HashMap<>();
            ArrayList<MyStudent> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(name,num,jsonData,numbers,true);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyStudent> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            if(myNameArrayList.size() >0) {
                TextView name = (TextView) findViewById(R.id.studentInfoNameCourse);
                name.setText(myNameArrayList.get(0).getName());

                TextView course = (TextView) findViewById(R.id.studentInfoCourseNameCourse);
                course.setText(myNameArrayList.get(0).getCourse());

                TextView attendance = (TextView)findViewById(R.id.studentInfoStatsCourse);
                attendance.setText("Total attendance : "+myNameArrayList.size()+"/30");

                Log.e("Name and Course", "updateUI: " + name + course);
                AdapterClassNew itemAdapter = new AdapterClassNew(this, myNameArrayList);
                listView.setAdapter(itemAdapter);
            }
            else {
                Toast.makeText(this, "No Such Student Found With Recent Transactions", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_admin_page);
            }

        }
        else if(dueClicked)
        {
            dueClicked = false;
            HashMap<Integer, MyStudent> myNameHashMap = new HashMap<>();
            ArrayList<MyStudent> myNameArrayList = new ArrayList<>();
            myNameHashMap = QueryUtils.extractRecord(name,courseDue,jsonData,numbers,true,false);
            Log.d("myNameHAshMAp", "updateUI: " + myNameArrayList);
            for (Map.Entry<Integer, MyStudent> eachName : myNameHashMap.entrySet()) {
                myNameArrayList.add(eachName.getValue());
            }
            if(myNameArrayList.size() >0) {
                TextView name = (TextView) findViewById(R.id.studentInfoNameCourse);
                name.setText(myNameArrayList.get(0).getName());

                TextView course = (TextView) findViewById(R.id.studentInfoCourseNameCourse);
                course.setText(myNameArrayList.get(0).getCourse());

                TextView attendance = (TextView)findViewById(R.id.studentInfoStatsCourse);
                attendance.setText("Total attendance : "+myNameArrayList.size()+"/30");

                Log.e("Name and Course", "updateUI: " + name + course);
                AdapterClassNew itemAdapter = new AdapterClassNew(this, myNameArrayList);
                listView.setAdapter(itemAdapter);
            }
            else {
                Toast.makeText(this, "No Such Student Found With Recent Transactions", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_admin_page);
            }
        }
    }
}
