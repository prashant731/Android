package com.developer.aditya.globalizers;

import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
    public final class QueryUtils {

        private QueryUtils(){

        }

        public static HashMap<Integer, MyName> extractRecord(String sampleJsonResponse, String course){
            HashMap<Integer,MyName> result = new HashMap<>();
            try {
                JSONArray jsonArray = new JSONArray(sampleJsonResponse);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject js = jsonArray.getJSONObject(i);
                    Log.e("JSonObject : ", "extractRecord: "+js);
                    String name="Dummy";
                    int number=0;
                    if(js.has("Course") && js.getString("Course").equalsIgnoreCase(course)){
                        if(js.has("Name")) name=js.getString("Name");
                        if(js.has("PersonalContact")) number=(js.getInt("PersonalContact"));
                        String numberS = number + " ";
                        Log.e("Name and number", "extractRecord: "+name + numberS);
                        result.put(i++,new MyName(name,numberS));
                    }
                }
            }
            catch (JSONException e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            return result;
        }

        public static HashMap<Integer, MyStudent> extractRecord(String username,String userId,String sampleJsonResponse,String[] studCon)
        {
            HashMap<Integer,MyStudent> result = new HashMap<>();
            String name=null;
//            int number=0;
//            int parent=0;
            String number=null;
            String parent=null;
            String id=null;
            String course = null;
            String timeStamp = null;
            try {
                JSONArray jsonArray = new JSONArray(sampleJsonResponse);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject js = jsonArray.getJSONObject(i);
                    Log.e("JSonObject : ", "extractRecord: "+js);
                    if((js.has("Name") && js.getString("Name").equalsIgnoreCase(username)) && (js.has("CardID") && js.getString("CardID").equalsIgnoreCase(userId)))
                    {
                        name=js.getString("Name");
                        id = js.getString("CardID");
                        if(js.has("PersonalContact")) number=(js.getString("PersonalContact"));
                        if(js.has("ParentContact")) parent = (js.getString("ParentContact"));
                        if(js.has("Course")) course= (js.getString("Course"));
                        if(js.has("TimeStamp"))  timeStamp = (js.getString("TimeStamp"));
                        Log.e("Name and number", "extractRecord: "+id+name + number + parent+course+timeStamp);
                        result.put(i++,new MyStudent(id,name,course,number,parent,timeStamp));
                    }
                }
            }
            catch (JSONException e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            if(number!=null || parent!=null){
                studCon[0]=number+"";
                studCon[1]=parent+"";}
            return result;
        }

        public static HashMap<Integer, MyDue> extractRecord(String sampleJsonResponse){
            HashMap<Integer,MyDue> result = new HashMap<>();
            try {
                JSONArray jsonArray = new JSONArray(sampleJsonResponse);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //Date myDate = dateFormat.parse(js.getString("Due Date"));
//                Date d = new Date();
//                String s  = DateFormat.format("yyyy-MM-dd", d.getTime()).toString();
//                Date myDate = dateFormat.parse(s);
//                Date newDate = new Date(myDate.getTime() + 864000000L);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                Date strDate = sdf.parse(valid_until);
//                if (new Date().after(strDate)) {
//                    catalog_outdated = 1;
//                Date newDate = new Date(myDate.getTime() - 604800000L);
//                String date = dateFormat.format(newDate);
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject js = jsonArray.getJSONObject(i);
                    Log.e("JSonObject : ", "extractRecord: " + js);
                    String name = "Dummy";
                    String number = "";
                    if (js.has("DueDate")) {
//                        String dateFound = js.getString("DueDate");
//                        Date strDate = sdf.parse();
//                        if(new Date().after(strDate)) {
                        if (js.has("Name")) name = js.getString("Name");
                        if (js.has("DueDate")) number = (js.getString("DueDate"));
                        String numberS = number + " ";
                        Log.e("Name and date", "extractRecord: " + name + numberS);
                        result.put(i++, new MyDue(name, numberS));
//                        }
                    }
                }

            }
            catch (JSONException e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            catch (Exception e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            return result;
        }

        public static HashMap<Integer, MyStats> extractRecord(String sampleJsonResponse,boolean d){
            HashMap<Integer,MyStats> result = new HashMap<>();
            try {
                JSONArray jsonArray = new JSONArray(sampleJsonResponse);
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject js = jsonArray.getJSONObject(i);
                    Log.e("JSonObject : ", "extractRecord: " + js);
                    String name = "Dummy";
                    String code = "";
                    String date = "";
                    if (js.has("Name")) name = js.getString("Name");
                    if (js.has("_id")) code = (js.getString("_id"));
                    if(js.has("DueDate")) date = js.getString("DueDate");
                        Log.e("Name and date", "extractRecord: " + name + code + date);
                        result.put(i++, new MyStats(name, code,date));
                    }

            }
            catch (JSONException e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            catch (Exception e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            return result;
        }

        public static HashMap<Integer, MyStudent> extractRecord(String username,String num,String sampleJsonResponse,String[] studCon,boolean n)
        {
            HashMap<Integer,MyStudent> result = new HashMap<>();
            String name=null;
//            int number=0;
//            int parent=0;
            String number=null;
            String parent=null;
            String id=null;
            String course = null;
            String timeStamp = null;
            try {
                JSONArray jsonArray = new JSONArray(sampleJsonResponse);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject js = jsonArray.getJSONObject(i);
                    Log.e("JSonObject : ", "extractRecord: "+js);
                    if((js.has("Name") && js.getString("Name").equalsIgnoreCase(username)) && (js.has("PersonalContact") && js.getString("PersonalContact").equalsIgnoreCase(num)))
                    {
                        name=js.getString("Name");
                        id = js.getString("CardID");
                        if(js.has("PersonalContact")) number=(js.getString("PersonalContact"));
                        if(js.has("ParentContact")) parent = (js.getString("ParentContact"));
                        if(js.has("Course")) course= (js.getString("Course"));
                        if(js.has("TimeStamp"))  timeStamp = (js.getString("TimeStamp"));
                        Log.e("Name and number", "extractRecord: "+id+name + number + parent+course+timeStamp);
                        result.put(i++,new MyStudent(id,name,course,number,parent,timeStamp));
                    }
                }
            }
            catch (JSONException e){
                Log.e("Query Utils : ", "extractRecord Problem: ", e);
            }
            if(number!=null || parent!=null){
                studCon[0]=number;
                studCon[1]=parent;}
            return result;
        }


    }