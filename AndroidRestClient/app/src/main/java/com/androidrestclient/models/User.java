package com.androidrestclient.models;


import java.util.ArrayList;

import com.androidrestclient.config.Config;


/**
 * Created by DotCreek on 15/07/2014.
 */
public class User extends Api{
    /*Attributes TODO User get and Setters*/
    public String id;
    public String first_name;

    /*List of users*/
    private  ArrayList<User> userList;

    /*Constructor for a new user*/
    public User(String id, String first_name){
        super(Config.USERSRESOURCE, id);
        this.first_name = first_name;
    }
    public User(){
        super(Config.USERSRESOURCE, null);
    }

}
