package com.androidrestclient.models;

import android.util.Log;

import com.androidrestclient.cnx.ApiModule;
import com.google.gson.JsonObject;


import retrofit.Callback;

public class Api {
    private String resource;
    private String restId;

    public Api(String resource, String id){
        this.resource = resource;
        this.restId = id;
    }

    public Api(){}

    public boolean save(Callback<Object> saveCallback){
        if(this.restId == null){
            /*Create new User*/
            ApiModule.getService().post(this.resource, this, saveCallback);
        }else{
            /*Update User*/
            ApiModule.getService().put(this.resource, this.restId, this, saveCallback);
        }
        return true;
    }

    public boolean delete(Callback<Object> deleteCallback){
        Log.e("USER IDDDDD", this.resource.toString());
        if(this.restId != null) {
            /*Delete*/
            ApiModule.getService().delete(this.resource, this.restId, deleteCallback);
        }
        return true;
    }

    public void get(Callback<JsonObject> indexCallback){
        ApiModule.getService().get(this.resource, indexCallback);
    }
}
