package com.androidrestclient.models;

import com.androidrestclient.config.Config;

import java.util.ArrayList;

/**
 * Created by DotCreek on 10/27/14.
 */
public class Provinces extends Api {
    public String name;
    public String _id;

    /*List of users*/
    private ArrayList<Provinces> userList;

    /*Constructor for a new user*/
    public Provinces(String id, String name){
        super(Config.PROVINCES, id);
        this.setName(name);
        this.set_id(id);
    }
    public Provinces(){
        super(Config.PROVINCES, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
