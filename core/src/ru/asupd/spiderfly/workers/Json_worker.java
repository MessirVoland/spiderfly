package ru.asupd.spiderfly.workers;

/**
 * Created by Asup.D on 21.12.2016.
 */
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.omg.DynamicAny.NameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import ru.asupd.spiderfly.states.GameState;


public class Json_worker {
    HttpURLConnection urlConnection = null;
    HttpURLConnection urlConnection2 = null;
    BufferedReader reader = null;
    String resultJson = "";
    String urli,urli2;
    Boolean internet;
    String out_json = "";

    /*public static class Data{
        //public String dbname;
        public TableEntry[] players;
    }
    public static class TableEntry {
        private String pid;
        private String namep;
       private String score;

        // public String version;
       // public String datatime;
    }*/
    public class Leaf{
        String pid;
        String namep;
        String score;
        String version;
        String data;
        public Leaf(){

        }
    }
    //public Json jet;
    //public TableEntry jet2;

    public String getResultJson() {
        return resultJson;
    }

    public Json_worker() {
        urli = "http://www.beltest.tk/get.php";
        internet =true;
        try {
            //URL url = new URL("http://androiddocs.ru/api/friends.json");
            URL url = new URL(urli);
            ;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();




            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();

        } catch (Exception e) {
            System.out.println(e+"\n" +"Возможно нет подключения к интернету");
            internet=false;
        }
    }

    public String execute_json(String json) {
        // Большой участок мертвого кода

        //JSONObject dataJsonObj = null;
        //out_json = json;
        //jet = new Json();
        //jet.setTypeName(null);
        //jet.setOutputType(JsonWriter.OutputType.json);
        //jet.setUsePrototypes(false);
        //jet.setIgnoreUnknownFields(true);
        //jet.setOutputType(JsonWriter.OutputType.json);
        //Data data = new Data();
        //jet2 = new TableEntry();
        //jet2.namep="tester1";
        //jet2.pid="1";
        //jet2.score="15";
        //JsonReader json2 = new JsonReader();
        //The Highscore of the Player1
        //data.players = new Array<TableEntry>();
        //data.players.add(jet2);
        //System.out.println("Фунциклирует? до джсона "+data.players.get(0).namep);
        // Data data2 = new Data();
        // data2.players = new Array<TableEntry>();
        //data =jet.fromJson(Data.class,json);
        // out_json2= jet.toJson(data);
        //  System.out.println(out_json2);
        //data2=jet.fromJson(Data.class,out_json2);
        // Gson gson2 = new Gson();
        // out_json = gson2.toJson(data2);
        //out_json=jet.toJson(data2);
        // System.out.println(json);
        //JsonValue value1 = new JsonReader().parse(json);
        //System.out.println(out_json);
        //JsonValue value1 = new JsonReader().parse("// Just a comment \n {foo: bar}");
        //  System.out.println(value1);
        // System.out.println(gson.toJson(data.players));
        //Leaf post = new Leaf();
        //data2= gson.fromJson(out_json, Data.class);
        //data.players.addAll(gson.fromJson(json, data.players.getClass()));
        //data<Leaf> postlif= Array.as
        //System.out.println("json = "+json);
        // System.out.println("Фунциклирует? "+post[0].namep);
        //String secondName = "";
        //System.out.println("Фунциклирует?"+data2.players.get(1).namep);
        // JsonValue base = json2.parse(json);
        // out_json = base.asString();
        //  data.players.add(jet2);
        //out_json=jet.toJson(data);
        // json=" {"+"players"+":[{"+"pid"+":"+"1"+","+"name"+":"+"tester1"+","+"score"+":"+"15"+"}]";
        //data = jet.fromJson(Data.class,json);
        //jet2=data.players.get(1);
        // out_json=jet2.name_p;
        // JSONArray friends = dataJsonObj.getJSONArray("players");
        // 1. достаем инфо о втором друге - индекс 1
        //JSONObject secondFriend = friends.getJSONObject(1);
        //secondName = secondFriend.getString("name");
        //Log.d(LOG_TAG, "Второе имя: " + secondName);
        /*for (int i = 0; i < friends.length(); i++) {
            JSONObject player = friends.getJSONObject(i);
            String pid = player.getString("pid");
            String name = player.getString("name");
            String score = player.getString("score");
            Log.d(LOG_TAG, "pip: " + pid);
            Log.d(LOG_TAG, "name: " + name);
            Log.d(LOG_TAG, "score: " + score);
            out_json+= "pip: " + pid+";name: " + name+";score: " + score+ "\n";
            }*/
        if (internet){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Leaf[] post=gson.fromJson(json,Leaf[].class);
        out_json = "";
        for (int i=0; i<post.length;i++){
            out_json+="Name : "+post[i].namep+", ";
            out_json+="Score : "+post[i].score+"\n";
        }
        return out_json;}
        else
        {
            out_json="No internet connection...";
            return out_json;
        }
    }
    public void package_json(String json) {

        System.out.println("Send!!!!: "+json);
       // GsonBuilder builder = new GsonBuilder();
       // Gson gson = builder.create();


        //HttpsURLConnection conn = (HttpsURLConnection) url2.openConnection();
        //conn.setReadTimeout(10000);
       // conn.setConnectTimeout(15000);
        //conn.setRequestMethod("POST");
        urli2 = "http://www.beltest.tk/set.php";
        try {
            String params="name=AndroidTester&score="+GameState.score+"&version=V.0.6";
            URL url2 = new URL(urli2);
            byte[] data = null;
            InputStream is = null;

            urlConnection2 = (HttpURLConnection) url2.openConnection();
            urlConnection2.setReadTimeout(10000);
            urlConnection2.setConnectTimeout(15000);
            urlConnection2.setRequestMethod("POST");
            urlConnection2.setDoInput(true);
            urlConnection2.setDoOutput(true);

            urlConnection2.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));

            OutputStream os = urlConnection2.getOutputStream();
            data = params.getBytes("UTF-8");
            os.write(data);
            data=null;
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            //writer.write("");
            //writer.flush();
            //writer.close();
            urlConnection2.connect();
            int response_code =urlConnection2.getResponseCode();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if (response_code == 200) {
                is = urlConnection2.getInputStream();

                byte[] buffer = new byte[8192]; // Такого вот размера буфер
                // Далее, например, вот так читаем ответ
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                data = baos.toByteArray();
                System.out.println( new String(data, "UTF-8"));
            }

        }catch (Exception e) {
            System.out.println(e+"\n" +"Возможно нет подключения к интернету");
            internet=false;
        }



    }
}

