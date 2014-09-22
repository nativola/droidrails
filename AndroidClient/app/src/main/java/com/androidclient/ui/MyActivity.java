package com.androidclient.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidclient.R;
import com.androidclient.ui.activities.CreateUserActivity;
import com.androidclient.dto.User;
import com.androidclient.ui.fragments.MainFragment;
import com.google.android.gms.plus.PlusClient;


public class MyActivity extends Activity {

    private MainFragment mainFragment;
    private PlusClient mPlusClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        listeners();
    }

    /*Adding all listener for the layout activity_main.xml*/
    private void listeners(){
        /** Listeners new user button */
        Button btnNewUser =(Button)findViewById(R.id.btn_new_user);
        if (btnNewUser != null){
            btnNewUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** Start up the activity for create user*/
                    Intent intent = new Intent(MyActivity.this, CreateUserActivity.class);
                    startActivity(intent);
                }
            });
        }
        final EditText email = (EditText)findViewById(R.id.auth_email);
        final EditText password = (EditText)findViewById(R.id.auth_password);


        Button btnAuth = (Button)findViewById(R.id.btn_auth);
        if (btnAuth != null){
            btnAuth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = new User(email.getText().toString(),password.getText().toString());
                    user.authenticate();

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
}
