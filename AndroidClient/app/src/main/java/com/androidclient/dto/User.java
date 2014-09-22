package com.androidclient.dto;
import android.util.Log;

import com.androidclient.cnx.ApiModule;
import com.androidclient.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by DotCreek on 15/07/2014.
 */
public class User{

    /*Attributes*/
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String authentication_token;
    private String encrypted_password;

    /*Errors Flags*/
    protected Boolean isError;
    protected String errorMessage;

    public User(){}

    /*Constructor for a new user*/
    public User(String first_name, String last_name, String email, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.encrypted_password = password;
    }

    /*Constructor for login user*/
    public User(String email, String password){
        this.email = email;
        this.encrypted_password = password;
    }

    /*
    * Public methods helpers
    * ======================/

    /** save new user, and save changes (update)*/
    public boolean save(){
        isError = false;
        if(this.id == null){
            /*Create new User*/
            ApiModule.getService().post(Config.USERSRESOURCE, this, this.saveCallback());
        }else{
            /*Update User*/
            ApiModule.getService().put(Config.USERSRESOURCE, this.id, this, this.saveCallback());
        }
        return isError;
    }


     private Callback<Object> saveCallback(){
        return new Callback<Object>() {
             @Override
             public void success(Object user, Response response) {
                 try{
                     JSONObject json = new JSONObject( new Gson().toJson(user));
                        /*Setting the token authentication and user id from the response*/
                     if (json.getBoolean("success")){
                         User.this.setAuthentication_token(json.getJSONObject("user").getString("authentication_token"));
                         User.this.setId(json.getJSONObject("user").getJSONObject("_id").getString("$oid"));
                     }else{
                         isError = true;
                         errorMessage = "";
                     }
                     //Log.i("MyActivity",new Gson().toJson(user).toString());
                 }catch (Exception e){
                     Log.e("USER ERROR:", e.getMessage());
                 }
             }
             @Override
             public void failure(RetrofitError error) {
                 Log.e("USER ERROR:", error.getMessage());
                 isError = true;
                 errorMessage = error.getMessage();
             }
        };
     }


    public void authenticate(){
         ApiModule.getService().get(Config.USERSRESOURCE,this, new Callback<Object>() {
            @Override
            public void success(Object user, Response response) {
//                setFirst_name(user.getFirst_name());
//                setLast_name(user.getLast_name());
//                setEmail(user.getEmail());
//                setAuthentication_token(user.authentication_token);
//                setId(user.id);
            }
            @Override
            public void failure(RetrofitError error) {
                isError = true;
                errorMessage = error.getMessage();
            }
        });
    }

    /*Attributes Encapsulates Fields*/
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthentication_token() {
        return authentication_token;
    }

    public void setAuthentication_token(String authentication_token) {
        this.authentication_token = authentication_token;
    }

    public String getPassword() {
        return encrypted_password;
    }

    public void setPassword(String password) {
        this.encrypted_password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}