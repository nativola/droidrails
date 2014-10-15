package dto;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cnx.ApiModule;
import config.Config;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by DotCreek on 15/07/2014.
 */
public class User{
    /*Attributes TODO User get and Setters*/
    public String id;
    public  String first_name;

    /*Errors Flags*/
    protected Boolean isError;
    protected String errorMessage;

    /*List of users*/
    private  ArrayList<User> userList;

    /*Constructor for a new user*/
    public User(String first_name){
        this.first_name = first_name;
    }
    public User(){ }

   /*
    * Public methods helpers
    * ======================/
    */

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
    /** remove user */
    public boolean delete(String id){
        isError = false;
        if(this.id != null) {
            /*Delete*/
            ApiModule.getService().delete(Config.USERSRESOURCE, id, this, this.deleteCallback());
        }
        return isError;
    }

    /** remove user */
    public ArrayList<User> get(){
        userList = new ArrayList<User>();
        ApiModule.getService().get(Config.USERSRESOURCE,  this.indexCallback());
        return userList;
    }

    private Callback<Object> saveCallback(){
        return new Callback<Object>() {
            @Override
            public void success(Object user, Response response) {
                try{
                    JSONObject json = new JSONObject( new Gson().toJson(user));
                     /*Setting the token authentication and user id from the response*/
                    User.this.first_name = (json.getJSONObject("user").getString("first_name"));
                    User.this.id = (json.getJSONObject("user").getJSONObject("_id").getString("$oid"));
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

    private Callback<Object> deleteCallback(){
        return new Callback<Object>() {
            @Override
            public void success(Object user, Response response) {
                Log.e("USER ERROR:", response.getBody().toString());
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("USER ERROR:", error.getMessage());
                isError = true;
                errorMessage = error.getMessage();
            }
        };
    }

    private Callback<Object> indexCallback(){
        return new Callback<Object>() {
            @Override
            public void success(Object users, Response response) {
                userList = (ArrayList)users;
                Log.i("USER LIST:", users.toString());
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("USER ERROR:", error.getMessage());
                isError = true;
                errorMessage = error.getMessage();
            }
        };
    }
}
