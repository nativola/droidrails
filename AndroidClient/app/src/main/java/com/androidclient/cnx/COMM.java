package com.androidclient.cnx;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;

/**
 * Created by HanzelCruz on 7/17/14.
 */
public class COMM {

    /*Replace by the endpoint api*/
    private static final String API_URL = "http://localhost:3000/";
    private RestAdapter cnx;
    /*Singleton*/
    private static COMM mInstance = null;

    public static COMM getmInstance(){
        if (mInstance == null){
            mInstance = new COMM();
        }
        return  mInstance;
    }

    private COMM() {
        /*Creating the communication with the api*/
        this.setCnx(new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build());
    }

    public RestAdapter getCnx() {
        return cnx;
    }

    private void setCnx(RestAdapter cnx) {
        this.cnx = cnx;
    }
}
