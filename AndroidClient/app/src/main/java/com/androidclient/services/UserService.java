package com.androidclient.services;
import android.util.Log;

import com.androidclient.dto.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by @HanzelCruz on 14/07/2014.
 */
public class UserService {
    public UserService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:3000")
                .build();
        TwitchTvApiInterface twitchTvService = restAdapter.create(TwitchTvApiInterface.class);
        twitchTvService.getStreams();
    }
}
interface TwitchTvApiInterface {
    @GET("/user")
    void getStreams();
}