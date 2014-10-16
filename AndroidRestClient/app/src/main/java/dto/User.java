package dto;


import java.util.ArrayList;
import java.util.List;
import cnx.ApiModule;
import config.Config;
import retrofit.Callback;


/**
 * Created by DotCreek on 15/07/2014.
 */
public class User{
    /*Attributes TODO User get and Setters*/
    public String id;
    public  String first_name;

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
    public boolean save(Callback<Object> saveCallback){
        if(this.id == null){
            /*Create new User*/
            ApiModule.getService().post(Config.USERSRESOURCE, this, saveCallback);
        }else{
            /*Update User*/
            ApiModule.getService().put(Config.USERSRESOURCE, this.id, this, saveCallback);
        }
        return true;
    }

    /** remove user */
    public boolean delete(Callback<Object> deleteCallback){
        if(this.id != null) {
            /*Delete*/
            ApiModule.getService().delete(Config.USERSRESOURCE, this.id, deleteCallback);
        }
        return true;
    }

    /** get all users */
    public void get(Callback<List> indexCallback){
        ApiModule.getService().get(Config.USERSRESOURCE, indexCallback);
    }

}
