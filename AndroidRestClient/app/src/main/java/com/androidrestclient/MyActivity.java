package com.androidrestclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.androidrestclient.adapters.NameAdapter;

import java.util.ArrayList;

import dto.User;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyActivity extends Activity {
    User user;
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        user = new User();
        userList = user.get();

        Button btnNewName = (Button)findViewById(R.id.btn_add_name);
        final EditText name = (EditText)findViewById(R.id.txt_name);

        btnNewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO create a MAIN API*/
                user = new User(name.getText().toString());
                if (user.save()){
                    Log.i("User", "Saved");
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        getNamesList();
    }


    private void getNamesList(){
        NameAdapter adapter = new NameAdapter(this, userList);
        Log.e("USER ERROR:", "adsfasdfasdfads");
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final User item = (User) parent.getItemAtPosition(position);
                user.delete(item.id);
            }
        });
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
