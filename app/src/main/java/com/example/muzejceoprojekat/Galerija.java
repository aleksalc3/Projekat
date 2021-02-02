package com.example.muzejceoprojekat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import org.codehaus.jackson.map.ObjectMapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Galerija extends AppCompatActivity {

    ListView listView;
    ProgressBar pb;
    ArrayList<Eksponat> listaEksponata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerija);

        listView = findViewById(R.id.lista);
        listaEksponata = new ArrayList<>();


        GetEksponat getEksponat = new GetEksponat();
        getEksponat.execute();
    }

    public class GetEksponat extends AsyncTask<String, Integer, JSONArray> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected JSONArray doInBackground(String... strings) {
            try {
                JSONArray nizJSON = new JSONArray();
                for (int i = 1; i < 30000; i += 300) {
                    URL url = new URL("https://collectionapi.metmuseum.org/public/collection/v1/objects/" + i);
                    System.out.println("URLLLLLLLLLL");
                    System.out.println(url);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        JSONObject json;
                        String line = bufferedReader.readLine();
                        json = new JSONObject(line);
                        nizJSON.put(json);
                        bufferedReader.close();
                    } else {
                        continue;
                    }

                }
                return nizJSON;
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            if (jsonArray == null) {
                Toast.makeText(Galerija.this, "Ne postoji eksponat!", Toast.LENGTH_SHORT).show();
                System.out.println("JSONOBJECT JE NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

            }
            if (jsonArray != null) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        int objectID = Integer.parseInt(jsonObject.getString("objectID"));
                        String country = jsonObject.getString("country");
                        String accessionYear = jsonObject.getString("accessionYear");
                        String imageURL = jsonObject.getString("primaryImage");

                        Eksponat e = new Eksponat(objectID, title, accessionYear, country, imageURL);
                        listaEksponata.add(e);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                MojAdapter adapter = new MojAdapter();
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(Galerija.this, Galerija_Drugi.class);
                        intent.putExtra("Eksponat", listaEksponata.get(i));
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public class MojAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listaEksponata.size();
        }

        @Override
        public Object getItem(int i) {
            return listaEksponata.get(i);
        }

        @Override
        public long getItemId(int i) {
            return listaEksponata.get(i).getObjectID();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.eksponat_item_list, null, false);

            TextView txtTitle = view.findViewById(R.id.txtTitle);
            txtTitle.setText(listaEksponata.get(i).getTitle());

            return view;
        }
    }
}
