package com.example.tugas_recycle.Activity;

import android.os.Bundle;

import com.example.tugas_recycle.Model.ListItem;
import com.example.tugas_recycle.Adapter.ResepAdapter;
import com.example.tugas_recycle.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<ListItem> mResepitem;
    private ArrayList<DetailActivity> mdetail;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.list_resep);
        mResepitem = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray resepArray = obj.getJSONArray("recipe");
            // implement for loop for getting users list data
            for (int i = 0; i < resepArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject resepDetail = resepArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                String nama=resepDetail.getString("name");
                String deskripsi=resepDetail.getString("description");
                String image=resepDetail.getString("image");

                mResepitem.add(new ListItem(nama,deskripsi,image));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        ResepAdapter customAdapter = new ResepAdapter(MainActivity.this, mResepitem);
        mRecyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
    public String loadJSONFromAsset() {

        String json = null;
        try {
            InputStream is = getAssets().open("recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
