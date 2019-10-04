package com.example.tugas_recycle.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugas_recycle.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_resep);
        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra("image");
        TextView namamakanann=findViewById(R.id.namamakanann);
        TextView deskripsimakanan=findViewById(R.id.deskripsimakanan);
        TextView amount=findViewById(R.id.amount);
        ImageView gambar=findViewById(R.id.gambarmakanan);
        namamakanann.setText(getIntent().getStringExtra("nama"));
        deskripsimakanan.setText(getIntent().getStringExtra("deskripsi"));
        Picasso.with(this).load(imageUrl).into(gambar);
        ArrayList<Double> amountt = null;
        String unit;
        String name;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONObject c = obj.getJSONObject("recipe");

            JSONArray ingredient = c.getJSONArray("ingredient");
            for (int i = 0; i<ingredient.length();i++){
                amountt.add(ingredient.getJSONObject(i).getDouble("amount"));

                unit = ingredient.getString(Integer.parseInt("unit"));
                name = ingredient.getString(Integer.parseInt("name"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String loadJSONFromAsset() {
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

