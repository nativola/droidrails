package cnx;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import config.Config;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by HanzelCruz on 7/17/14.
 */
public class ApiModule {
    private static RestAdapter restAdapter;
    private static InterfaceApiRest service;

    public static RestAdapter getProvideRestAdapter(){
        if (restAdapter == null){
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Config.APIURL)
                    .build();
        }
        return restAdapter;
    }

    public static InterfaceApiRest getService(){
        if (service == null){
            service = getProvideRestAdapter().create(InterfaceApiRest.class);
        }
        return service;
    }
}
