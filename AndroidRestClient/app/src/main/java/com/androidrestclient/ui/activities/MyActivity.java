package com.androidrestclient.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.androidrestclient.R;
import com.androidrestclient.ui.adapters.UserAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.androidrestclient.models.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyActivity extends Activity {
    User user;
    UserAdapter adapter;

    Button btnSave ;
    Button btnNew ;
    Button btnProvinces;
    EditText txtName ;

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        btnSave = (Button)findViewById(R.id.btn_add_name);
        btnNew = (Button)findViewById(R.id.btn_new);
        btnProvinces = (Button)findViewById(R.id.btn_provinces);
        txtName = (EditText)findViewById(R.id.txt_name);

        user = new User();
        user.get(indexCallback());
        /**
         * Click save Button event
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().matches("")){
                   Toast toast = Toast.makeText(MyActivity.this, "Add new Name", Toast.LENGTH_SHORT);
                   toast.show();
                   return;
                }
                user.first_name = txtName.getText().toString();
                if (user.save(saveCallback())) {
                    txtName.setText("");
                    Log.i("User", "Saved");
                }
            }
        });

        /**
         * Click New user event
         */
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                txtName.setText("");
            }
        });

        /**
         * Click on Provinces Button
         */
        btnProvinces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MyActivity.this, ProvincesActivity.class);
                MyActivity.this.startActivity(myIntent);
            }
        });
    }

    /*
    *Callback for get the user list
    */
    private Callback<JsonObject> indexCallback(){
        return new Callback<JsonObject>() {
            @Override
            public void success(JsonObject userJson, Response response) {
                /*Convert the list to list of user*/
                try{
                    Type listType = new TypeToken<ArrayList<User>>() { }.getType();
                    userList = new Gson().fromJson(userJson.getAsJsonArray("data"), listType);
                    /*Setting up the adapter*/
                    adapter = new UserAdapter(MyActivity.this, userList);
                    ListView listView = (ListView) findViewById(R.id.listView);
                    listView.setAdapter(adapter);
                    setListeners(listView);
                }catch (Exception e){
                    Log.e("USER ERROR:", e.getMessage());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("USER ERROR:", error.getMessage());
            }
        };
    }

    /*Adding listeners to listview*/
    private void setListeners(ListView listView){
         /*Listeners events */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                user = new User(((User) parent.getItemAtPosition(position)).id,
                        ((User) parent.getItemAtPosition(position)).first_name);

                txtName.setText(user.first_name);
            }
        });
        /*Long click event for remove*/
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                user = new User(((User) parent.getItemAtPosition(position)).id,
                        ((User) parent.getItemAtPosition(position)).first_name);

                new AlertDialog.Builder(MyActivity.this)
                        .setTitle("Remove?")
                        .setMessage("Do you really want to remove?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                user.delete(deleteCallback());
                                user = new User();
                                userList.remove(position);
                                adapter.notifyDataSetChanged();
                                txtName.setText("");
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return true;
            }
        });
    }
    /*
    *Callback for save user
    */
    private Callback<Object> saveCallback(){
        return new Callback<Object>() {
            @Override
            public void success(Object user, Response response) {
                Log.e("USER", user.toString());
                try{
                    Type listType = new TypeToken<User>() { }.getType();
                    /*New user */
                    if (MyActivity.this.user.id == null){
                        userList.add((User)new Gson().fromJson(user.toString(), listType));
                    }
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Log.e("USER ERROR:", e.getMessage()+"error");
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("USER ERROR:", error.getMessage()+"error");
            }
        };
    }
    /*
    * Callback remove user
    */
    private Callback<Object> deleteCallback(){
        return new Callback<Object>() {
            @Override
            public void success(Object user, Response response) {
                Log.e("USER REMOVED:", user.toString());
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
        getMenuInflater().inflate(R.menu.my, menu);
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
