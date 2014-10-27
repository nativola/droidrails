package com.androidrestclient.ui.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.androidrestclient.R;
import com.androidrestclient.models.Provinces;
import com.androidrestclient.ui.adapters.ProvinceAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProvincesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinces);

        Provinces provinces = new Provinces();
        provinces.get(indexCallback());

    }
    /*
        *Callback for get the user list
        */
    private Callback<JsonObject> indexCallback(){
        return new Callback<JsonObject>() {
            @Override
            public void success(JsonObject userJson, Response response) {

                try{
                    Type listType = new TypeToken<ArrayList<Provinces>>() { }.getType();
                    List<Provinces> provincesList = new Gson().fromJson(userJson.getAsJsonArray("provinces"), listType);
                    /*Setting up the adapter*/
                    Log.e("USE:", provincesList.toString());
                    ProvinceAdapter adapter = new ProvinceAdapter(ProvincesActivity.this, provincesList);
                    ListView listView = (ListView) findViewById(R.id.privinces_list);
                    listView.setAdapter(adapter);
                }catch (Exception e){
                    Log.e("USER ERROR:", e.getMessage()+"Error");
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("USER ERROR:", error.getMessage());
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.provinces, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
